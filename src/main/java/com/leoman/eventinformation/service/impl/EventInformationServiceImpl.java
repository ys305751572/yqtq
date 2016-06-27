package com.leoman.eventinformation.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.eventinformation.dao.EventInformationDao;
import com.leoman.eventinformation.entity.EventInformation;
import com.leoman.eventinformation.service.EventInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/27.
 */
@Service
public class EventInformationServiceImpl extends GenericManagerImpl<EventInformation,EventInformationDao> implements EventInformationService{

    @Autowired
    private EventInformationDao dao;

}
