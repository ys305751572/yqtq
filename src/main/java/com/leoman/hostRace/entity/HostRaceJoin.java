package com.leoman.hostRace.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/2.
 */
@Entity
@Table(name = "t_host_race_join")
public class HostRaceJoin extends BaseEntity {

    @Column(name = "host_race_id")
    private Long hostRaceId;

    @Column(name = "team_id")
    private Long teamId;

    public Long getHostRaceId() {
        return hostRaceId;
    }

    public void setHostRaceId(Long hostRaceId) {
        this.hostRaceId = hostRaceId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
