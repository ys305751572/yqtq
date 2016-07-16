package com.leoman.stadium.dao;

import com.leoman.admin.entity.Admin;
import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.stadium.entity.StadiumUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

/**
 * Created by Administrator on 2016/6/12.
 */
public interface StadiumUserDao extends IBaseJpaRepository<StadiumUser> {

    //球场场地数
    @Query("SELECT COUNT(a) FROM StadiumSub a WHERE a.stadiumId IN (SELECT b.id FROM Stadium b WHERE b.stadiumUserId = ?1)")
    public Integer stadiumNum(Long id);

    //当天的金额
    @Query("SELECT SUM(a.price) FROM StadiumBooking a WHERE a.stadium.id IN (SELECT b.id FROM Stadium b WHERE b.stadiumUserId = ?1) AND a.createDate >= ?2")
    public Double toDaySumPrice(Long id,Long date);

    //散客数量
    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.stadium.id IN (SELECT b.id FROM Stadium b WHERE b.stadiumUserId = ?1) AND a.type=0")
    public Integer individualNum(Long id);

    //球场积累金额
    @Query("SELECT SUM(a.price) FROM StadiumBooking a WHERE a.stadium.id = ?1")
    public Integer accumulatedAmount(Long id);

    //球场主登录
    @Query("select a from StadiumUser a where a.username = ?1")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    public StadiumUser findByUsername(String username);
}
