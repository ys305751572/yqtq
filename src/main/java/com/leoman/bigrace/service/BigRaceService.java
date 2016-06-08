package com.leoman.bigrace.service;

import com.leoman.bigrace.entity.BigRace;
import com.leoman.common.service.GenericManager;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/8.
 */
public interface BigRaceService extends GenericManager<BigRace>{

    public Page<BigRace> findAll(BigRace bigRace, Integer currentPage, Integer pageSize) throws Exception;

}
