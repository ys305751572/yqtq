package com.leoman.index.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.security.entity.Module;
import com.leoman.user.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface IndexDao extends IBaseJpaRepository<User>{

    //权限子模块
    @Query("SELECT m FROM Module m WHERE m.id IN (SELECT rm.moduleId FROM RoleModule rm WHERE rm.roleId IN (SELECT ur.role.id FROM UserRole ur WHERE ur.adminId = ?1))")
    public List<Module> moduleList(Long id);
    //父模块
    @Query("SELECT a FROM Module a WHERE a.id IN (?1)")
    public List<Module> parentModelueList(List<Long> ids);
    //当天新增的用户
    @Query("SELECT COUNT(a) FROM User a WHERE a.createDate >= ?1")
    public Integer newUserNum(Long date);
    //当天新增的会员
    @Query("SELECT COUNT(a) FROM UserVip a WHERE a.createDate >= ?1")
    public Integer newUserVipNum(Long date);
    //当天新增的散客约球
    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.createDate >= ?1")
    public Integer newReserveNum(Long date);
    //当天新增的约场地
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.createDate >= ?1")
    public Integer newStadiumBookingNum(Long date);
    //当天新增的球队
    @Query("SELECT COUNT(a) FROM Team a WHERE a.createDate >= ?1")
    public Integer newTeamNum(Long date);
    //当天新增的看球
    @Query("SELECT COUNT(a) FROM GirlUser a WHERE a.createDate >= ?1")
    public Integer newGirlUserNum(Long date);
    //当天新增的帖子
    @Query("SELECT COUNT(a) FROM Post a WHERE a.createDate >= ?1")
    public Integer newPostNum(Long date);
    //当天新增的提现
    @Query("SELECT COUNT(a) FROM StadiumUserWithdraw a WHERE a.createDate >= ?1")
    public Integer newWithdrawNum(Long date);

    //场地订单统计信息:
    //已定
    @Query("SELECT COUNT(a) FROM StadiumBooking a")
    public Integer allStadiumBookingNum();
    //已使用
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.status='1'")
    public Integer useStadiumBookingNum();
    //未使用
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.status='0'")
    public Integer notUsedStadiumBookingNum();
    //全部金额
    @Query("SELECT SUM(a.price) FROM StadiumBooking a")
    public Integer allStadiumBookingprice();

    //散客统计信息:
    //组队数
    @Query("SELECT COUNT(a) FROM Reserve a")
    public Integer allReserveNum();
    //组队成功数
    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.status='1'")
    public Integer successReserveNum();
    //组队失败数
    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.status='2'")
    public Integer failureReserveNum();
    //总金额
    @Query("SELECT SUM(a.price) FROM Reserve a")
    public Integer allReservePrice();
    //确认金额
    @Query("SELECT SUM(a.price) FROM Reserve a WHERE a.status='1' OR a.status='3'")
    public Integer confirmReservePrice();
    //退回金额
    @Query("SELECT SUM(a.price) FROM Reserve a WHERE a.status='2'")
    public Integer backReservePrice();

    //看球统计信息:
    //宝贝订单
    @Query("SELECT COUNT(a) FROM GirlUser a")
    public Integer allGirlUserNum();
    //订单金额
    @Query("SELECT SUM(a.price) FROM GirlUser a")
    public Integer allGirlUserprice();
    //直播看球邀请次数
    @Query("SELECT SUM(a.invitation) FROM WatchingRace a")
    public Integer watchingRaceInvitation();


    //访问统计:


    //---------------------------------场主端--------------------------------------

    //最新订单
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.createDate >= ?1 AND a.stadium.stadiumUserId=?2")
    public Integer newStadiumBooking(Long daten,Long id);

    //退场订单
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.createDate >= ?1 AND a.stadium.stadiumUserId=?2 AND a.status = '2' ")
    public Integer exitStadiumBooking(Long date,Long id);

    //成功订单
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.createDate >= ?1 AND a.stadium.stadiumUserId=?2 AND a.status = '1' ")
    public Integer successfulStadiumBooking(Long date,Long id);

    //场地订单


    //金额统计:
    //按时间查询
    @Query("SELECT SUM(a.price) FROM StadiumSub a WHERE a.stadiumId IN (SELECT b FROM Stadium b WHERE b.stadiumUserId = ?1) AND a.createDate >=?2")
    public Integer income(Long id,Long date);

    @Query("SELECT SUM(a.price) FROM StadiumSub a WHERE a.stadiumId IN (SELECT b FROM Stadium b WHERE b.stadiumUserId = ?1)")
    public Integer sumIncome(Long id);

}
