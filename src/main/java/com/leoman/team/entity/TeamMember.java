package com.leoman.team.entity;//package com.leoman.team.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/5/26.
 */
@Entity
@Table(name = "t_team_member")
public class TeamMember extends BaseEntity  {

    //用户id
    @Column(name = "user_id")
    private Long userId;
    //球队id
    @Column(name = "team_id")
    private Long teamId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
