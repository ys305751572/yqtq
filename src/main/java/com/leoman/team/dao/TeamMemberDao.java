package com.leoman.team.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.team.entity.TeamMember;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public interface TeamMemberDao extends IBaseJpaRepository<TeamMember>{

    @Query("SELECT a FROM TeamMember a WHERE a.teamId=?1")
    public List<TeamMember> findByTeamId(Long id);
}
