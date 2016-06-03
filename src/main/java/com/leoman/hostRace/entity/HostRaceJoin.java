package com.leoman.hostRace.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.team.entity.Team;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/2.
 */
@Entity
@Table(name = "t_host_race_join")
public class HostRaceJoin extends BaseEntity {

    @Column(name = "host_race_id")
    private Long hostRaceId;

//    @Column(name = "team_id")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getHostRaceId() {
        return hostRaceId;
    }

    public void setHostRaceId(Long hostRaceId) {
        this.hostRaceId = hostRaceId;
    }

}
