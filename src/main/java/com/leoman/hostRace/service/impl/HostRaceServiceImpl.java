package com.leoman.hostRace.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.hostRace.dao.HostRaceDao;
import com.leoman.hostRace.entity.HostRace;
import com.leoman.hostRace.service.HostRaceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/2.
 */
@Service
public class HostRaceServiceImpl extends GenericManagerImpl<HostRace, HostRaceDao> implements HostRaceService {
    @Autowired
    private HostRaceDao dao;

    @Override
    public Page<HostRace> findAll(HostRace hostRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<HostRace> spec = buildSpecification(hostRace);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public HostRace findById(Long id) {
        return dao.findById(id);
    }

}
