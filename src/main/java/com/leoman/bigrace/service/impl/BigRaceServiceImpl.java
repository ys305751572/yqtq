package com.leoman.bigrace.service.impl;

import com.leoman.bigrace.dao.BigRaceDao;
import com.leoman.bigrace.entity.BigRace;
import com.leoman.bigrace.service.BigRaceService;
import com.leoman.common.service.impl.GenericManagerImpl;
import org.apache.commons.lang.StringUtils;
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
    public Page<BigRace> findAll(String teamName,BigRace bigRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<BigRace> spec = buildSpecification(bigRace,teamName);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<BigRace> buildSpecification(final BigRace b,final String teamName) {
        return new Specification<BigRace>() {
            @Override
            public Predicate toPredicate(Root<BigRace> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate p = null;
                if(StringUtils.isNotBlank(teamName)) {
                    p = cb.like(root.get("team1name").as(String.class),"%" + teamName + "%");
                    p = cb.or(p,cb.like(root.get("team2name").as(String.class),"%" + teamName + "%"));
                }
                if(b.getStadium().getCity().getCityId() != null){
                    if(p != null) {
                        p = cb.and(p,cb.equal(root.get("stadium").get("city").get("cityId").as(Long.class), b.getStadium().getCity().getCityId()));
                    }else {
                        p = cb.equal(root.get("stadium").get("city").get("cityId").as(Long.class), b.getStadium().getCity().getCityId());
                        cb.and(p);
                    }
                }
                return p;
            }
        };
    }
}
