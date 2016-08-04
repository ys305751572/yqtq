package com.leoman.bigrace.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;
import com.leoman.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/8.
 */
@Entity
@Table(name = "t_big_race")
public class BigRace extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    //比赛名称
    @Column(name = "name")
    private String name;
    //球队1名称
    @Column(name = "team1name")
    private String team1name;
    //球队1队徽
    @Column(name = "avater1")
    private String avater1;
    //球队2名称
    @Column(name = "team2name")
    private String team2name;
    //球队2队徽
    @Column(name = "avater2")
    private String avater2;
    //状态: 0:正常 1:关闭
    @Column(name = "status")
    private Integer status;
    //比赛时间
    @Column(name = "start_date")
    private Long startDate;
    //介绍
    @Column(name = "description")
    private String description;

    @Transient
    private String avater1AbsolutePath;

    @Transient
    private String avater2AbsolutePath;

    public String getAvater2AbsolutePath() {
        if(StringUtils.isNotBlank(avater2)){
            return ConfigUtil.getString("upload.url") + getAvater2();
        }else {
            return "";
        }
    }

    public void setAvater2AbsolutePath(String avater2AbsolutePath) {
        this.avater2AbsolutePath = avater2AbsolutePath;
    }

    public String getAvater1AbsolutePath() {
        if(StringUtils.isNotBlank(avater1)){
            return ConfigUtil.getString("upload.url") + getAvater1();
        }else {
            return "";
        }
    }

    public void setAvater1AbsolutePath(String avater1AbsolutePath) {
        this.avater1AbsolutePath = avater1AbsolutePath;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam1name() {
        return team1name;
    }

    public void setTeam1name(String team1name) {
        this.team1name = team1name;
    }

    public String getAvater1() {
        return avater1;
    }

    public void setAvater1(String avater1) {
        this.avater1 = avater1;
    }

    public String getTeam2name() {
        return team2name;
    }

    public void setTeam2name(String team2name) {
        this.team2name = team2name;
    }

    public String getAvater2() {
        return avater2;
    }

    public void setAvater2(String avater2) {
        this.avater2 = avater2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
