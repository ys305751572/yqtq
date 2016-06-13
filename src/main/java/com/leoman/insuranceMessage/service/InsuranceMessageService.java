package com.leoman.insuranceMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.insuranceMessage.entity.InsuranceMessage;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface InsuranceMessageService extends GenericManager<InsuranceMessage>  {

    public InsuranceMessage findById(Long id);
}
