package com.leoman.bigrace.service.impl;

import com.leoman.bigrace.dao.BigRaceDao;
import com.leoman.bigrace.entity.BigRace;
import com.leoman.bigrace.service.BigRaceService;
import com.leoman.common.service.impl.GenericManagerImpl;
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
 * Created by Administrator on 2016/6/8.
 */
@Service
public class BigRaceServiceImpl extends GenericManagerImpl<BigRace,BigRaceDao> implements BigRaceService{

    @Autowired
    private BigRaceDao dao;

    @Override
    public Page<BigRace> findAll(BigRace bigRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<BigRace> spec = buildSpecification(bigRace);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<BigRace> buildSpecification(final BigRace b) {
        return new Specification<BigRace>() {
            @Override
            public Predicate toPredicate(Root<BigRace> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (b.getName() != null) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + b.getName() + "%"));
                }

                if(b.getStadium().getCity().getCityId() != null){
                    list.add(cb.equal(root.get("stadium").get("city").get("cityId").as(Long.class),b.getStadium().getCity().getCityId()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
