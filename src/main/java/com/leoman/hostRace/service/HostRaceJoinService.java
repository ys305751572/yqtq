package com.leoman.hostRace.service;

import com.leoman.common.service.GenericManager;
import com.leoman.hostRace.entity.HostRaceJoin;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/3.
 */
public interface HostRaceJoinService extends GenericManager<HostRaceJoin> {

    public Page<HostRaceJoin> findAll(HostRaceJoin hostRaceJoin, Integer currentPage, Integer pageSize) throws Exception;

}
