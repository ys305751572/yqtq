package com.leoman.vipsetting.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.vipsetting.dao.SystemVipLevelDao;
import com.leoman.vipsetting.entity.SystemVipLevel;
import com.leoman.vipsetting.service.SystemVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service
public class SystemVipLevelServiceImpl extends GenericManagerImpl<SystemVipLevel,SystemVipLevelDao> implements SystemVipLevelService{

    @Autowired
    private SystemVipLevelDao dao;
    @Autowired
    private EntityManagerFactory factory;
    @Override
    public List<SystemVipLevel> OrderByLevel(){
        return dao.OrderByLevel();
    }

}
