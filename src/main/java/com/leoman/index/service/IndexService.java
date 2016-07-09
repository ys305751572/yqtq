package com.leoman.index.service;

import com.leoman.common.service.GenericManager;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface IndexService{

    public Integer newUserNum();

    public Integer newAdminNum();

    public Integer newReserveNum();

    public Integer newStadiumBookingNum();

    public Integer newTeamNum();

    public Integer newGirlUserNum();

    public Integer newPostNum();

    public Integer newWithdrawNum();

    public Integer allStadiumBookingNum();

    public Integer useStadiumBookingNum();

    public Integer notUsedStadiumBookingNum();

    public Integer allStadiumBookingprice();

    public Integer allReserveNum();

    public Integer successReserveNum();

    public Integer failureReserveNum();

    public Integer allReservePrice();

    public Integer confirmReservePrice();

    public Integer backReservePrice();

    public Integer allGirlUserNum();

    public Integer allGirlUserprice();;

    public Integer watchingRaceInvitation();

}