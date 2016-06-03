package com.leoman.hostRace.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.hostRace.dao.HostRaceJoinDao;
import com.leoman.hostRace.entity.HostRaceJoin;
import com.leoman.hostRace.service.HostRaceJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
@Service
public class HostRaceJoinServiceImpl extends GenericManagerImpl<HostRaceJoin, HostRaceJoinDao> implements HostRaceJoinService {

    @Autowired
    private HostRaceJoinDao dao;

    @Override
    public Page<HostRaceJoin> findAll(HostRaceJoin hostRaceJoin, Integer currentPage, Integer pageSize) throws Exception {
        Specification<HostRaceJoin> spec = buildSpecification(hostRaceJoin);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }


    public Specification<HostRaceJoin> buildSpecification(final HostRaceJoin h) {
        return new Specification<HostRaceJoin>() {
            @Override
            public Predicate toPredicate(Root<HostRaceJoin> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(h.getHostRaceId() != null) {
                    list.add(cb.equal(root.get("hostRaceId").as(Long.class), h.getHostRaceId()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
