package com.leoman.team.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.team.entity.TeamRace;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface TeamRaceDao extends IBaseJpaRepository<TeamRace> {

    public TeamRace findById(Long id);

    public List<TeamRace> findAll();
}
