package com.leoman.index.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.index.dao.IndexDao;
import com.leoman.index.service.IndexService;
import com.leoman.utils.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/7/7.
 */
@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    private IndexDao dao;
    //当天新增的用户
    @Override
    public Integer newUserNum() {
        return dao.newUserNum(TestUtil.getTimesmorning());
    }
    //当天新增的会员
    @Override
    public Integer newAdminNum() {
        return dao.newAdminNum(TestUtil.getTimesmorning());
    }
    //当天新增的散客约球
    @Override
    public Integer newReserveNum() {
        return dao.newReserveNum(TestUtil.getTimesmorning());
    }
    //当天新增的约场地
    @Override
    public Integer newStadiumBookingNum() {
        return dao.newStadiumBookingNum(TestUtil.getTimesmorning());
    }
    //当天新增的球队
    @Override
    public Integer newTeamNum() {
        return dao.newTeamNum(TestUtil.getTimesmorning());
    }
    //当天新增的看球
    @Override
    public Integer newGirlUserNum() {
        return dao.newGirlUserNum(TestUtil.getTimesmorning());
    }
    //当天新增的帖子
    @Override
    public Integer newPostNum() {
        return dao.newPostNum(TestUtil.getTimesmorning());
    }
    //当天新增的提现
    @Override
    public Integer newWithdrawNum() {
        return dao.newWithdrawNum(TestUtil.getTimesmorning());
    }
}
