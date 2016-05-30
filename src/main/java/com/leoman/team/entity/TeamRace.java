package com.leoman.team.entity;//package com.leoman.team.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
@Table(name = "t_team_race")
public class TeamRace  extends BaseEntity{

    //主队id
    @Column(name = "home_team_id")
    private Long homeTeamId;

    //客队id
    @Column(name = "visiting_team_id")
    private Long visitingTeamId;

    //城市id
    @Column(name = "city_id")
    private Long cityId;

    //球场id
    @Column(name = "stadium_id")
    private Long stadiumId;

    //比赛开始时间
    @Column(name = "start_date")
    private Long startDate;

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getVisitingTeamId() {
        return visitingTeamId;
    }

    public void setVisitingTeamId(Long visitingTeamId) {
        this.visitingTeamId = visitingTeamId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }
}
