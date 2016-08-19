package com.leoman.reserve.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumSub;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.user.entity.User;

import javax.persistence.*;

/**
 * 散人预约
 * Created by Administrator on 2016/5/24.
 */
@Entity
@Table(name = "t_reserve")
public class Reserve extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

//    @ManyToOne
//    @JoinColumn(name = "city_id")
//    private City city;

    @Column(name = "city_id")
    private Long cityId;

    //场次
    @ManyToOne
    @JoinColumn(name = "site_id")
    private StadiumSub stadiumSub;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SystemInsurance systemInsurance;

    //球场类型 N人制
    @Column(name = "match_type")
    private Integer matchType;

    //价格
    @Column(name = "price")
    private Double price;

    //付款方式 0:AA 1:全额
    @Column(name = "payment")
    private Integer payment;


    //球赛类型 0:私人 1:公共
    @Column(name = "type")
    private Integer type;

    //比赛开始时间
    @Column(name = "start_date")
    private Long startDate;

    //时长
    @Column(name = "time")
    private Integer time;

    //状态0:正在组队1:组队成功2:组队失败3:比赛结束
    @Column(name = "status")
    private Integer status;

    //预定类型 0:散客 1:其他
    @Column(name = "reserve_type")
    private Integer reserveType;

    //支付状态（0：未支付，1：已支付）
    @Column(name = "pay_status")
    private Integer payStatus;




    @Transient
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SystemInsurance getSystemInsurance() {
        return systemInsurance;
    }

    public void setSystemInsurance(SystemInsurance systemInsurance) {
        this.systemInsurance = systemInsurance;
    }

    public Integer getReserveType() {
        return reserveType;
    }

    public void setReserveType(Integer reserveType) {
        this.reserveType = reserveType;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public StadiumSub getStadiumSub() {
        return stadiumSub;
    }

    public void setStadiumSub(StadiumSub stadiumSub) {
        this.stadiumSub = stadiumSub;
    }
}
