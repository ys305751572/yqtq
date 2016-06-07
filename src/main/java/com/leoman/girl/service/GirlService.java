package com.leoman.girl.service;

import com.leoman.common.service.GenericManager;
import com.leoman.girl.entity.Girl;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface GirlService extends GenericManager<Girl> {

    public Page<Girl> findAll(Girl girl, Integer currentPage, Integer pageSize) throws Exception;

}
