package com.leoman.girl.service;

import com.leoman.common.service.GenericManager;
import com.leoman.girl.entity.GirlComment;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/8.
 */
public interface GirlCommentService extends GenericManager<GirlComment> {

    public Page<GirlComment> findAll(GirlComment girlComment, Integer currentPage, Integer pageSize) throws Exception;
}
