package com.leoman.team.entity;

import com.leoman.common.entity.BaseEntity;
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
    @Column(name = "city_id")
    private Long cityId;
    //口号
    @Column(name = "slogan")
    private String slogan;
    //队标(LOGO)url
    @Column(name = "avater")
    private String avater;
    //队长ID
    @Column(name = "leader_user_id")
    private Long leaderUserId;

    //对应球队球员
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Set<TeamMember> tmSet;

//    //主队
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "home_team_id")
//    private Set<TeamRace> trSet;
//    //客队
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "visiting_team_id")
//    private Set<TeamRace> trvSet;

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
//
//    public Set<TeamRace> getTrvSet() {
//        return trvSet;
//    }
//
//    public void setTrvSet(Set<TeamRace> trvSet) {
//        this.trvSet = trvSet;
//    }

    public Set<TeamMember> getTmSet() {
        return tmSet;
    }

    public void setTmSet(Set<TeamMember> tmSet) {
        this.tmSet = tmSet;
    }

//    public Set<TeamRace> getTrSet() {
//        return trSet;
//    }
//
//    public void setTrSet(Set<TeamRace> trSet) {
//        this.trSet = trSet;
//    }

    public String getName() {
        return name;
    }public void setName(String name) {
        this.name = name;
    }public Long getCityId() {
        return cityId;
    }public void setCityId(Long cityId) {
        this.cityId = cityId;
    }public String getSlogan() {
        return slogan;
    }public void setSlogan(String slogan) {
        this.slogan = slogan;
    }public String getAvater() {
        return avater;
    }public void setAvater(String avater) {
        this.avater = avater;
    }public Long getLeaderUserId() {
        return leaderUserId;
    }public void setLeaderUserId(Long leaderUserId) {
        this.leaderUserId = leaderUserId;
    }


}
