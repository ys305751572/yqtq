package com.leoman.team.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.User;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/25.
 */
@Entity
@Table(name = "t_team")
public class Team extends BaseEntity {

    //球队名称
    @Column(name = "name")
    private String name;
    //城市ID
//    @Column(name = "city_id")
//    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    //口号
    @Column(name = "slogan")
    private String slogan;
    //队标(LOGO)url
    @Column(name = "avater")
    private String avater;
    //队长ID
//    @Column(name = "leader_user_id")
//    private Long leaderUserId;
    @ManyToOne
    @JoinColumn(name = "leader_user_id",referencedColumnName = "user_id")
    private User user;

    //对应球队球员
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Set<TeamMember> tmSet;

    //场次
    @Transient
    private Integer tmSize;

    //球员数量
    @Transient
    private Integer tmSetNum;

    public Integer getTmSetNum() {
        return tmSet.size();
    }

    public void setTmSetNum(Integer tmSetNum) {
        this.tmSetNum = tmSetNum;
    }

    public Integer getTmSize() {
        return tmSize;
    }

    public void setTmSize(Integer tmSize) {
        this.tmSize = tmSize;
    }

    public Set<TeamMember> getTmSet() {
        return tmSet;
    }

    public void setTmSet(Set<TeamMember> tmSet) {
        this.tmSet = tmSet;
    }

    public String getName() {
        return name;
    }public void setName(String name) {
        this.name = name;
    }public String getSlogan() {
        return slogan;
    }public void setSlogan(String slogan) {
        this.slogan = slogan;
    }public String getAvater() {
        return avater;
    }public void setAvater(String avater) {
        this.avater = avater;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
