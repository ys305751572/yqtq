package com.leoman.girl.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;
import com.leoman.team.entity.TeamRace;
import com.leoman.user.entity.User;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/6.
 */
//约看表
@Entity
@Table(name = "t_girl_user")
public class GirlUser extends BaseEntity{

    //宝贝ID
    @ManyToOne
    @JoinColumn(name = "girl_id")
    private Girl girl;
    //用户ID
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //球场ID
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    //预约时间
    @Column(name = "start_date")
    private Long startDate;
    //赛事ID
    @ManyToOne
    @JoinColumn(name = "race_id")
    private TeamRace teamRace;
    //价格
    @Column(name = "price")
    private Double price;
    //小费
    @Column(name = "tip")
    private Double tip;
    //时长
    @Column(name = "duration")
    private Integer duration;

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public TeamRace getTeamRace() {
        return teamRace;
    }

    public void setTeamRace(TeamRace teamRace) {
        this.teamRace = teamRace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
