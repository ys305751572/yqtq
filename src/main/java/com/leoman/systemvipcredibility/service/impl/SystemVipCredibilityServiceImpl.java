package com.leoman.systemvipcredibility.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.systemvipcredibility.dao.SystemVipCredibilityDao;
import com.leoman.systemvipcredibility.entity.SystemVipCredibility;
import com.leoman.systemvipcredibility.service.SystemVipCredibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Service
public class SystemVipCredibilityServiceImpl extends GenericManagerImpl<SystemVipCredibility,SystemVipCredibilityDao> implements SystemVipCredibilityService{

    @Autowired
    private SystemVipCredibilityDao dao;

    @Override
    public List<SystemVipCredibility> credibilityList(Long systemVipId, Integer action) {
        return dao.credibilityList(systemVipId,action);
    }
}
