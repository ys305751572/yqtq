package com.leoman.team.service;

import com.leoman.common.service.GenericManager;
import org.springframework.data.domain.Page;

import java.util.List;
import com.leoman.team.entity.Team;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface TeamService extends GenericManager<Team> {
    //sql查询list
    public List<Team> findList(Long id,Long name,Long cityId,int start,int length);

    public Team findById(Long id) throws Exception ;

    public Page<Team> findAll(String details,Team team, Integer currentPage, Integer pageSize) throws Exception;

    public  List<Team> findAll();

    public Integer findTmSize(Long teamId);
}
