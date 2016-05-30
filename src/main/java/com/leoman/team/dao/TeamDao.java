package com.leoman.team.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.team.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface TeamDao extends IBaseJpaRepository<Team> {

    public Team findById(Long id);

}
