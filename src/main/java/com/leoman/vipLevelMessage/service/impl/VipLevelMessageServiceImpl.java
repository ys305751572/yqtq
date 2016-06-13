package com.leoman.vipLevelMessage.service.impl;

import com.leoman.activity.dao.ActivityDao;
import com.leoman.activity.entity.Activity;
import com.leoman.activity.service.ActivityService;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.vipLevelMessage.dao.VipLevelMessageDao;
import com.leoman.vipLevelMessage.entity.VipLevelMessage;
import com.leoman.vipLevelMessage.service.VipLevelMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class VipLevelMessageServiceImpl extends GenericManagerImpl<VipLevelMessage, VipLevelMessageDao> implements VipLevelMessageService {

    @Autowired
    private VipLevelMessageDao vipLevelMessageDao;

    @Override
    public VipLevelMessage findById(Long id) {
        return vipLevelMessageDao.findById(id);
    }
}
