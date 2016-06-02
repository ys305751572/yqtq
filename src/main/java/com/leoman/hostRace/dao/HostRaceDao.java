package com.leoman.hostRace.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.hostRace.entity.HostRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface HostRaceDao extends IBaseJpaRepository<HostRace> {

    public HostRace findById(Long id);
}
