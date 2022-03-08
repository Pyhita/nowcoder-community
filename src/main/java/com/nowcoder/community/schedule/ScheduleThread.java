package com.nowcoder.community.schedule;

import com.mysql.cj.log.LogFactory;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: pyhita
 * @Date: 2022/3/2
 * @Descrption: com.nowcoder.community.schedule
 * @Version: 1.0
 */

// 定时计算帖子的分数
@Component
public class ScheduleThread implements CommunityConstant  {

    private static final Logger log = LoggerFactory.getLogger(ScheduleThread.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CommentService commentService;

    private static final Date epoch;
    static {
        try {
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("初始化牛客纪元失败！");
        }
    }
    // 1 hours = 1000 * 60 * 60
    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void scheduledTask() {
        String redisKey = RedisKeyUtil.getPostScoreKey();
        BoundSetOperations boundSetOps = redisTemplate.boundSetOps(redisKey);
        if (boundSetOps.size() == 0) {
            log.info("[任务取消] 没有需要刷新的帖子！");
            return;
        }

        log.info("任务开始，正在刷新帖子分数！");
        while (boundSetOps.size() > 0) {
            this.refresh((Integer) boundSetOps.pop());
        }
        log.info("任务结束，帖子分数刷新完成！");

    }

    private void refresh(int postId) {
        DiscussPost post = discussPostService.findDiscussPostById(postId);
        if (post == null) {
            log.info("该帖子不存在:id=" + postId);
        }


        // 评论数量
        int commentCount = post.getCommentCount();
        // 点赞数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, postId);

        // 计算权重
        double w = commentCount * 10 + likeCount * 2;
        // 分数=帖子权重+距离天数
        // w可能小于1，因为log存在，所以送入log的最小值应该为0
        // getTime()单位为ms
        double score = Math.log10(Math.max(1, w)) +
                (post.getCreateTime().getTime() - epoch.getTime()) / (3600 * 60 * 24);

        // 更新帖子分数
        discussPostService.updateScore(postId, score);
        // 更新elasticsearch
        post.setScore(score);
    }

}
