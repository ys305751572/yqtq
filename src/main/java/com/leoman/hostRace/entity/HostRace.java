package com.leoman.hostRace.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/2.
 */
@Entity
@Table(name = "t_host_race")
public class HostRace extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "avater")
    private String avater;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "match_type")
    private Integer matchType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "host_race_id")
    private Set<HostRaceJoin> hrSet;
    //队伍数
    @Transient
    private Integer hrNum;

    public Integer getHrNum() {
        return hrSet.size();
    }

    public void setHrNum(Integer hrNum) {
        this.hrNum = hrNum;
    }

    public Set<HostRaceJoin> getHrSet() {
        return hrSet;
    }

    public void setHrSet(Set<HostRaceJoin> hrSet) {
        this.hrSet = hrSet;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return matchType;
    }

    public void setType(Integer matchType) {
        this.matchType = matchType;
    }
}
