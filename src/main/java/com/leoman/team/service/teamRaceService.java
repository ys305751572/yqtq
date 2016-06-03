package com.leoman.team.service;

import com.leoman.common.service.GenericManager;
import com.leoman.team.entity.TeamRace;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface TeamRaceService extends GenericManager<TeamRace> {

    public TeamRace findById(Long id) throws Exception ;

    public Page<TeamRace> findAll(TeamRace teamRace,String teamName, Integer currentPage, Integer pageSize) throws Exception;
}
