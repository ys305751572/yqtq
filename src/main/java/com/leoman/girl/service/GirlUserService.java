package com.leoman.girl.service;

import com.leoman.common.service.GenericManager;
import com.leoman.girl.entity.GirlUser;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface GirlUserService extends GenericManager<GirlUser> {

    public Page<GirlUser> findAll(GirlUser girlUser, Integer currentPage, Integer pageSize) throws Exception;
}
