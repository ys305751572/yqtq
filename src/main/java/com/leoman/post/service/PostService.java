package com.leoman.post.service;

import com.leoman.common.service.ICommonService;
import com.leoman.post.entity.Post;
import com.leoman.reserve.entity.Reserve;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/7 0007 下午 4:31.
 */
public interface PostService extends ICommonService<Post> {

    public Page<Post> findPage(String details,String nickName, String content, Integer status, int pagenum, int pagesize);
}