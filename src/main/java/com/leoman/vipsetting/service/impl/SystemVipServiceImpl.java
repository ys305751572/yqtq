package com.leoman.vipsetting.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.vipsetting.dao.SystemVipDao;
import com.leoman.vipsetting.entity.SystemVip;
import com.leoman.vipsetting.service.SystemVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service
public class SystemVipServiceImpl extends GenericManagerImpl<SystemVip,SystemVipDao> implements SystemVipService {

    @Autowired
    private SystemVipDao dao;

}
