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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
@Service
public class HostRaceServiceImpl extends GenericManagerImpl<HostRace, HostRaceDao> implements HostRaceService {
    @Autowired
    private HostRaceDao dao;
    @Autowired
    private EntityManagerFactory factory;

    @Override
    public Page<HostRace> findAll(HostRace hostRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<HostRace> spec = buildSpecification(hostRace);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public HostRace findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Integer matchTeam(Long id){
        EntityManager em = factory.createEntityManager();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT                      ");
        sql.append("   COUNT(1)                  ");
        sql.append(" FROM                        ");
        sql.append("   t_host_race_join          ");
        sql.append(" WHERE host_race_id = "+id+" ");
        Query query = em.createNativeQuery(sql.toString());
        Integer num = Integer.parseInt(query.getResultList().get(0).toString());
        em.close();
        return num;
    }

}

