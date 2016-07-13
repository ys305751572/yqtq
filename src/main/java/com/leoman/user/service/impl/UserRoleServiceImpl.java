package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.user.dao.UserRoleDao;
import com.leoman.user.entity.UserRole;
import com.leoman.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Administrator on 2016/7/13.
 */
@Service
public class UserRoleServiceImpl extends GenericManagerImpl<UserRole,UserRoleDao> implements UserRoleService{

    @Autowired
    private UserRoleDao dao;

}
