package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Event;
import com.nowcoder.community.event.EventProducer;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @Author: pyhita
 * @Date: 2022/2/26
 * @Descrption: com.nowcoder.community.controller
 * @Version: 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController implements CommunityConstant {

    @Autowired
    private CommentService commentService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        // 触发评论事件
        Event event = new Event()
                .setUserid(hostHolder.getUser().getId())
                .setEntityType(comment.getEntityType())
                .setEntityId(comment.getEntityId())
                .setTopic(TOPIC_COMMENT)
                .setData("postId", discussPostId);
        // 判断评论的是帖子还是评论，设置entityUserId
        if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {
            Comment target = commentService.getCommentById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        } else if (comment.getEntityType() == ENTITY_TYPE_POST) {
            DiscussPost target = discussPostService.findDiscussPostById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        }

        eventProducer.fireEvent(event);

        return "redirect:/discuss/detail/" + discussPostId;
    }

}

