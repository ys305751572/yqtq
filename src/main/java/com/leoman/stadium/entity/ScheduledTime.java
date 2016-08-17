package com.leoman.stadium.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 球场主-参数设置-可预订时间
 * Created by Administrator on 2016/8/11.
 */
@Entity
@Table(name = "t_scheduled_time")
public class ScheduledTime extends BaseEntity{

    @Column(name = "stadium_user_id")
    private Long stadiumUserId;

    @Column(name = "scheduled_time")
    private Integer scheduledTime;

    public Integer getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Integer scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Long getStadiumUserId() {
        return stadiumUserId;
    }

    public void setStadiumUserId(Long stadiumUserId) {
        this.stadiumUserId = stadiumUserId;
    }
}
