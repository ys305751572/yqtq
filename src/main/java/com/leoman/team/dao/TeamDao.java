package com.leoman.team.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.team.entity.Team;
import com.leoman.team.entity.TeamRace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface TeamDao extends IBaseJpaRepository<Team> {

    public List<Team> findAll();

    public Team findById(Long id);

    @Query("SELECT COUNT(a) FROM TeamRace a WHERE a.homeTeam.id=?1 OR a.visitingTeam.id=?1")
    public Integer findTmSize(Long id);

}
