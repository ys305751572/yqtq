package com.leoman.post.dao;

import com.leoman.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 下午 5:02.
 */
public interface PostImageDao extends JpaRepository<PostImage, Integer> {

    //根据帖子id筛选帖子图片
    @Query("select a from PostImage a where a.post.id = ?1 ")
    public List<PostImage> findByPostId(Long postId);
}