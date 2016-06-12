package com.leoman.reserve.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "match_type")
    private Integer matchType;

    @Column(name = "price")
    private Double price;

    @Column(name = "payment")
    private Integer payment;

    @ManyToOne
    @JoinColumn(name = "insurance_id")
    private SystemInsurance systemInsurance;

    @Column(name = "type")
    private Integer type;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "time")
    private Integer time;

    @Column(name = "status")
    private Integer status;

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
}
