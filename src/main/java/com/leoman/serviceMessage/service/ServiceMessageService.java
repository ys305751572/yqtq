package com.leoman.serviceMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.serviceMessage.entity.ServiceMessage;


/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface ServiceMessageService extends GenericManager<ServiceMessage>  {

    public ServiceMessage findById(Long id);
}
