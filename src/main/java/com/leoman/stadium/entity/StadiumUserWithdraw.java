package com.leoman.stadium.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/16.
 */
@Entity
@Table(name = "t_stadium_user_withdraw")
public class StadiumUserWithdraw extends BaseEntity{
    //球场主ID
    @ManyToOne
    @JoinColumn(name = "stadium_user_id")
    private StadiumUser stadiumUser;

    //提现金额
    @Column(name = "withdraw_money")
    private Double withdrawMoney;

    //状态 0:未转账 1:转账中 2:已转账 3:驳回
    @Column(name = "status")
    private Integer status;

    public StadiumUser getStadiumUser() {
        return stadiumUser;
    }

    public void setStadiumUser(StadiumUser stadiumUser) {
        this.stadiumUser = stadiumUser;
    }

    public Double getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(Double withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
