package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.ScheduledTimeDao;
import com.leoman.stadium.entity.ScheduledTime;
import com.leoman.stadium.service.ScheduledTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/11.
 */
@Service
public class ScheduledTimeServiceImpl extends GenericManagerImpl<ScheduledTime,ScheduledTimeDao> implements ScheduledTimeService{

    @Autowired
    private ScheduledTimeDao dao;

}
