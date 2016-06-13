package com.leoman.vipMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.vipMessage.dao.VipMessageDao;
import com.leoman.vipMessage.entity.VipMessage;
import com.leoman.vipMessage.service.VipMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class VipMessageServiceImpl extends GenericManagerImpl<VipMessage, VipMessageDao> implements VipMessageService {

    @Autowired
    private VipMessageDao vipMessageDao;

    @Override
    public VipMessage findById(Long id) {
        return vipMessageDao.findById(id);
    }
}
