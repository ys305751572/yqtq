package com.leoman.viplevemessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.viplevemessage.dao.VipLeveMessageDao;
import com.leoman.viplevemessage.entity.VipLeveMessage;
import com.leoman.viplevemessage.service.VipLeveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13.
 */
@Service
public class VipLeveMessageServiceImpl extends GenericManagerImpl<VipLeveMessage,VipLeveMessageDao> implements VipLeveMessageService{

    @Autowired
    private VipLeveMessageDao dao;

}
