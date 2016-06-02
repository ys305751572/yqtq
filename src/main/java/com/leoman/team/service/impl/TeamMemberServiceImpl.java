package com.leoman.team.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.team.dao.TeamMemberDao;
import com.leoman.team.entity.TeamMember;
import com.leoman.team.service.TeamMemberService;
import com.leoman.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
@Service
public class TeamMemberServiceImpl extends GenericManagerImpl<TeamMember, TeamMemberDao> implements TeamMemberService {

    @Autowired
    private TeamMemberDao dao;

    @Autowired
    private EntityManagerFactory factory;

    @Override
    public List<TeamMember> findByTeamId(Long id) {
        return dao.findByTeamId(id);
    }

    @Override
    public List<User> findByAvater(Long id) {
        return dao.findByAvater(id);
    }

}
