package com.leoman.index.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.index.dao.IndexDao;
import com.leoman.index.service.IndexService;
import com.leoman.security.entity.Module;
import com.leoman.utils.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    private IndexDao dao;

    public List<Module> moduleList(Long id){
        return dao.moduleList(id);
    }

    public List<Module> parentModelueList(List<Long> ids){
        return dao.parentModelueList(ids);
    }

    //当天新增的用户
    @Override
    public Integer newUserNum() {
        return dao.newUserNum(TestUtil.getTimesmorning());
    }
    //当天新增的会员
    @Override
    public Integer newUserVipNum() {
        return dao.newUserVipNum(TestUtil.getTimesmorning());
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

    //场地订单统计信息:
    @Override
    public Integer allStadiumBookingNum() {
        return dao.allStadiumBookingNum();
    }
    @Override
    public Integer useStadiumBookingNum() {
        return dao.useStadiumBookingNum();
    }
    @Override
    public Integer notUsedStadiumBookingNum() {
        return dao.notUsedStadiumBookingNum();
    }
    @Override
    public Integer allStadiumBookingprice() {
        return dao.allStadiumBookingprice();
    }

    //散客统计信息:
    @Override
    public Integer allReserveNum() {
        return dao.allReserveNum();
    }
    @Override
    public Integer successReserveNum() {
        return dao.successReserveNum();
    }
    @Override
    public Integer failureReserveNum() {
        return dao.failureReserveNum();
    }
    @Override
    public Integer allReservePrice() {
        return dao.allReservePrice();
    }
    @Override
    public Integer confirmReservePrice() {
        return dao.confirmReservePrice();
    }
    @Override
    public Integer backReservePrice() {
        return dao.backReservePrice();
    }

    //看球统计信息:
    @Override
    public Integer allGirlUserNum() {
        return dao.allGirlUserNum();
    }
    @Override
    public Integer allGirlUserprice() {
        return dao.allGirlUserprice();
    }
    @Override
    public Integer watchingRaceInvitation() {
        return dao.watchingRaceInvitation();
    }

}
