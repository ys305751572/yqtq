package com.leoman.girl.service;

import com.leoman.common.service.GenericManager;
import com.leoman.girl.entity.Girl;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface GirlService extends GenericManager<Girl> {

    public Page<Girl> findAll(Integer appointment,Girl girl, Integer currentPage, Integer pageSize) throws Exception;

    public Integer findSize(Long id);

}
