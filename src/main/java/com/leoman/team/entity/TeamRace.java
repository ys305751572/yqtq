package com.leoman.team.entity;//package com.leoman.team.entity;

import com.leoman.city.entity.City;
import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
@Table(name = "t_team_race")
public class TeamRace  extends BaseEntity{

    //主队id
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    //客队id
    @ManyToOne
    @JoinColumn(name = "visiting_team_id")
    private Team visitingTeam;

    //城市id
//    @Column(name = "city_id")
//    private Long cityId;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    //球场id
//    @Column(name = "stadium_id")
//    private Long stadiumId;
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    //状态 0:等待 1:成功 2.失败
    @Column(name = "status")
    private Integer status;

    //比赛开始时间
    @Column(name = "start_date")
    private Long startDate;

    public TeamRace() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public void setVisitingTeam(Team visitingTeam) {
        this.visitingTeam = visitingTeam;
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
}
