package com.leoman.stadium.service;

import com.leoman.common.service.GenericManager;
import com.leoman.stadium.entity.StadiumUserWithdraw;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/16.
 */
public interface StadiumUserWithdrawService extends GenericManager<StadiumUserWithdraw> {

    public Page<StadiumUserWithdraw> findAll(String details,StadiumUserWithdraw stadiumUserWithdraw, Integer currentPage, Integer pageSize) throws Exception;

}
