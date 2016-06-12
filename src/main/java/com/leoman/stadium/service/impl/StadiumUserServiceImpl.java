package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumUserDao;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/12.
 */
@Service
public class StadiumUserServiceImpl extends GenericManagerImpl<StadiumUser,StadiumUserDao> implements StadiumUserService{

    @Autowired
    private StadiumUserDao dao;

}
