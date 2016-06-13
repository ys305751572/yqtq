package com.leoman.user.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/13.
 */
@Entity
@Table(name = "t_user_vip")
public class UserVip extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    //充值时长
    @Column(name = "duration")
    private Integer duration;
    //会员结束时间
    @Column(name = "vip_end_date")
    private Long vipEndDate;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(Long vipEndDate) {
        this.vipEndDate = vipEndDate;
    }
}
