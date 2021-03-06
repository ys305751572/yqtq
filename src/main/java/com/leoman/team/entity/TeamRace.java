package com.leoman.team.entity;//package com.leoman.team.entity;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
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

    //省份id
    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "province_id")
    private Province province;
    //城市id
    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;
    //比赛地址
    @Column(name = "address")
    private String address;

//    //球场id
//    @ManyToOne
//    @JoinColumn(name = "stadium_id")
//    private Stadium stadium;

    //状态 0:等待 1:成功 2.失败
    @Column(name = "status")
    private Integer status;

    //比赛开始时间
    @Column(name = "start_date")
    private Long startDate;

    public TeamRace() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

//    public Stadium getStadium() {
//        return stadium;
//    }
//
//    public void setStadium(Stadium stadium) {
//        this.stadium = stadium;
//    }


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }
}
