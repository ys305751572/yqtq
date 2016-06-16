package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumBookingDao;
import com.leoman.stadium.entity.StadiumBooking;
import com.leoman.stadium.service.StadiumBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/16.
 */
@Service
public class StadiumBookingServiceImpl extends GenericManagerImpl<StadiumBooking,StadiumBookingDao> implements StadiumBookingService{

    @Autowired
    private StadiumBookingDao dao;

    @Override
    public Page<StadiumBooking> findAll(StadiumBooking stadiumBooking, Integer currentPage, Integer pageSize) throws Exception {
        Specification<StadiumBooking> spec = buildSpecification(stadiumBooking);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

}
