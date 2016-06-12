package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.user.dao.UserReserveJoinDao;
import com.leoman.user.entity.UserReserveJoin;
import com.leoman.user.service.UserReserveJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
@Service
public class UserReserveJoinServiceImpl extends GenericManagerImpl<UserReserveJoin,UserReserveJoinDao> implements UserReserveJoinService {

    @Autowired
    private UserReserveJoinDao dao;

}
