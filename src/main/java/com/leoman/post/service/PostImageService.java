package com.leoman.post.service;

import com.leoman.post.entity.PostImage;
import com.leoman.common.service.ICommonService;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007 下午 5:03.
 */
public interface PostImageService extends ICommonService<PostImage> {

    public List<PostImage> findByPostId(Long postId);
}