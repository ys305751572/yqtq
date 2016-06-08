package com.leoman.post.dao;

import com.leoman.post.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 下午 5:00.
 */
public interface PostCommentDao extends JpaRepository<PostComment, Integer> {

    //根据帖子id筛选评论
    @Query("select a from PostComment a where a.post.id = ?1 ")
    public List<PostComment> findByPostId(Long postId);
}