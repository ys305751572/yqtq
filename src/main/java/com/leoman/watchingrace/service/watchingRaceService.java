package com.leoman.watchingrace.service;

import com.leoman.common.service.GenericManager;
import com.leoman.watchingrace.entity.WatchingRace;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/8.
 */
public interface WatchingRaceService extends GenericManager<WatchingRace>{

    public Page<WatchingRace> findAll(WatchingRace watchingRace, Integer currentPage, Integer pageSize) throws Exception;


}
