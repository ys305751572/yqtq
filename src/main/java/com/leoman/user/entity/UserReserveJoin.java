package com.leoman.user.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/7.
 */
//球场(比赛.散客)加入表
@Entity
@Table(name = "t_user_reserve_join")
public class UserReserveJoin extends BaseEntity{
    //预定用户ID
    @Column(name = "user_id")
    private Long userId;

    //预定比赛ID
    @Column(name = "reserve_id")
    private Long reserveId;

    //公共球场才有的标题
    @Column(name = "title")
    private String title;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
