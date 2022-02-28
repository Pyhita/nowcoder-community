package com.nowcoder.community.service;

import com.nowcoder.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * @Author: pyhita
 * @Date: 2022/2/28
 * @Descrption: com.nowcoder.community.service
 * @Version: 1.0
 */
@Service
public class LikeService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 点赞的方法
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);

                Boolean ismember = redisOperations.opsForSet().isMember(entityLikeKey, userId);

                redisOperations.multi();
                /**
                 * 两种情况：
                 * 1 member：true，说明此用户之前已经点赞了，此时实在取消赞
                 * 2 member：false，说明用户进行点赞
                 */
                if (ismember) {
                    redisOperations.opsForSet().remove(entityLikeKey, userId);
                    redisOperations.opsForValue().decrement(userLikeKey);
                } else {
                    redisOperations.opsForSet().add(entityLikeKey, userId);
                    redisOperations.opsForValue().increment(userLikeKey);
                }
                return redisOperations.exec();
            }
        });
    }

    // 查询某个实体点赞的数量
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    // 查询某人堆某实体点赞的状态
    public int findEntityLikeStatus(int userId, int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    // 查询某个用户获得的赞
    public int findUserLikeCount(int userId) {
        String userKey = RedisKeyUtil.getUserKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userKey);
        return count == null ? 0 : count;
    }




}