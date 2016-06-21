package com.leoman.stadium.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/12.
 */
//球场主个人信息表
@Entity
@Table(name = "t_stadium_user")
public class StadiumUser extends BaseEntity{

    @Column(name = "nick_name")
    private String nickName;
    //账号
    @Column(name = "username")
    private String username;
    //密码
    @Column(name = "password")
    private String password;
    //电话
    @Column(name = "mobile")
    private String mobile;
    //预定总金额
    @Column(name = "reserve_money")
    private Double reserveMoney;
    //提现总金额
    @Column(name = "withdraw_money")
    private Double withdrawMoney;
    //余额
    @Column(name = "balance")
    private Double balance;
    //城市ID
    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;
    //状态 0:正常 1:冻结
    @Column(name = "status")
    private Integer status;
    //场地数量
    @Transient
    private Integer stadiumNum;
    //每天总收入
    @Transient
    private Double toDaySumPrice;
    //散客人数
    @Transient
    private Integer individualNum;

    public Integer getIndividualNum() {
        return individualNum;
    }

    public void setIndividualNum(Integer individualNum) {
        this.individualNum = individualNum;
    }

    public Double getToDaySumPrice() {
        return toDaySumPrice;
    }

    public void setToDaySumPrice(Double toDaySumPrice) {
        this.toDaySumPrice = toDaySumPrice;
    }

    public Integer getStadiumNum() {
        return stadiumNum;
    }

    public void setStadiumNum(Integer stadiumNum) {
        this.stadiumNum = stadiumNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public Double getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(Double withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
