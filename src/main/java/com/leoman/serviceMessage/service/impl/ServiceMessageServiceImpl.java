package com.leoman.serviceMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.serviceMessage.dao.ServiceMessageDao;
import com.leoman.serviceMessage.entity.ServiceMessage;
import com.leoman.serviceMessage.service.ServiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class ServiceMessageServiceImpl extends GenericManagerImpl<ServiceMessage, ServiceMessageDao> implements ServiceMessageService {

    @Autowired
    private ServiceMessageDao serviceMessageDao;

    @Override
    public ServiceMessage findById(Long id) {
        return serviceMessageDao.findById(id);
    }
}
