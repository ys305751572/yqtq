package com.leoman.stadium.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    private String userName;
    //密码
    @Column(name = "password")
    private String password;
    //电话
    @Column(name = "mobile")
    private String mobile;
    //预定总金额
    @Column(name = "reserve_money")
    private Double reserveMoney;
    //体现总金额
    @Column(name = "withdraw_money")
    private Double withdrawMoney;
    //余额
    @Column(name = "balance")
    private Double balance;
    //城市ID
    @Column(name = "city_id")
    private Long cityId;
    //状态 0:正常 1:冻结
    @Column(name = "status")
    private Integer status;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
