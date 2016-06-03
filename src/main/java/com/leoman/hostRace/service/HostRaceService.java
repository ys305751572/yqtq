package com.leoman.hostRace.service;

import com.leoman.common.service.GenericManager;
import com.leoman.hostRace.entity.HostRace;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/2.
 */
public interface HostRaceService extends GenericManager<HostRace> {

    public Page<HostRace> findAll(HostRace hostRace, Integer currentPage, Integer pageSize) throws Exception;

    public HostRace findById(Long id);

}
