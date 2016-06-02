package com.leoman.team.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.team.entity.TeamMember;
import com.leoman.user.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public interface TeamMemberDao extends IBaseJpaRepository<TeamMember>{

    //查询每个队员的头像地址
    @Query("SELECT a FROM User a WHERE a.userId IN (SELECT b.userId FROM TeamMember b WHERE b.teamId=?1)")
    public List<User> findByAvater(Long id);

    @Query("SELECT a FROM TeamMember a WHERE a.teamId=?1")
    public List<TeamMember> findByTeamId(Long id);
}
