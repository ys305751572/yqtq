package com.leoman.team.service;

import com.leoman.common.service.GenericManager;
import com.leoman.team.entity.TeamMember;
import com.leoman.user.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public interface TeamMemberService extends GenericManager<TeamMember> {

    public List<TeamMember> findByTeamId(Long id);

    public List<User> findByAvater(Long id);

}
