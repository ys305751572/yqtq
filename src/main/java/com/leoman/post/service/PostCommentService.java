package com.leoman.post.service;

import com.leoman.common.service.ICommonService;
import com.leoman.post.entity.PostComment;

import java.util.List;


/**
 * Created by Administrator on 2016/6/7 0007 下午 5:00.
 */
public interface PostCommentService extends ICommonService<PostComment> {

    public List<PostComment> findByPostId(Long postId);
}