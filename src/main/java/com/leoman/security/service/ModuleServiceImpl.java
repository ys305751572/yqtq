package com.leoman.security.service;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.dao.ModuleDao;
import com.leoman.security.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/5/22.
 */
@Service
public class ModuleServiceImpl extends GenericManagerImpl<Module,ModuleDao> implements ModuleService{

    @Autowired
    private ModuleDao dao;
}
