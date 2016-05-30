package com.leoman.team.service;

import com.leoman.team.entity.TeamMember;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
public interface TeamMemberService {

    public List<TeamMember> findByTeamId(Long id);

}
