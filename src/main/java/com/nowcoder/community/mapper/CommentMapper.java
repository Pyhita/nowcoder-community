package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: pyhita
 * @Date: 2022/2/26
 * @Descrption: com.nowcoder.community.mapper
 * @Version: 1.0
 */
@Mapper
public interface CommentMapper {

    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

}
