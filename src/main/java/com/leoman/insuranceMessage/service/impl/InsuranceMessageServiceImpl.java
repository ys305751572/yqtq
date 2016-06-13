package com.leoman.insuranceMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.insuranceMessage.dao.InsuranceMessageDao;
import com.leoman.insuranceMessage.entity.InsuranceMessage;
import com.leoman.insuranceMessage.service.InsuranceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class InsuranceMessageServiceImpl extends GenericManagerImpl<InsuranceMessage, InsuranceMessageDao> implements InsuranceMessageService {

    @Autowired
    private InsuranceMessageDao insuranceMessageDao;

    @Override
    public InsuranceMessage findById(Long id) {
        return insuranceMessageDao.findById(id);
    }
}
