package com.leoman.post.dao;

import com.leoman.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/7 0007 下午 4:24.
 */
public interface PostDao extends JpaRepository<Post, Long>,JpaSpecificationExecutor<Post> {

}