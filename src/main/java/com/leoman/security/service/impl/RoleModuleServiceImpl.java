package com.leoman.security.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.dao.RoleModuleDao;
import com.leoman.security.entity.RoleModule;
import com.leoman.security.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/7/12.
 */
@Service
public class RoleModuleServiceImpl extends GenericManagerImpl<RoleModule,RoleModuleDao> implements RoleModuleService {

    @Autowired
    private RoleModuleDao dao;

}
