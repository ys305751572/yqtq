package com.leoman.stadium.service;

import com.leoman.common.service.GenericManager;
import com.leoman.stadium.entity.StadiumBooking;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/16.
 */
public interface StadiumBookingService extends GenericManager<StadiumBooking>{

    public Page<StadiumBooking> findAll(StadiumBooking stadiumBooking, Integer currentPage, Integer pageSize) throws Exception;
}
