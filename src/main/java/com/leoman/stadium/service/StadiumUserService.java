package com.leoman.stadium.service;

import com.leoman.common.service.GenericManager;
import com.leoman.stadium.entity.StadiumUser;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/12.
 */
public interface StadiumUserService extends GenericManager<StadiumUser>{

    public Page<StadiumUser> findAll(StadiumUser stadiumUser, Integer currentPage, Integer pageSize) throws Exception;

    public Integer stadiumNum(Long id);

    public Double toDaySumPrice(Long id);

    public Integer individualNum(Long id);
}
