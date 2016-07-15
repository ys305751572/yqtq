package com.leoman.watchingrace.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.watchingrace.dao.WatchingRaceDao;
import com.leoman.watchingrace.entity.WatchingRace;
import com.leoman.watchingrace.service.WatchingRaceService;
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
public class WatchingRaceServiceImpl extends GenericManagerImpl<WatchingRace,WatchingRaceDao> implements WatchingRaceService{

    @Autowired
    private WatchingRaceDao dao;

    @Override
    public Page<WatchingRace> findAll(WatchingRace watchingRace, Integer currentPage, Integer pageSize) throws Exception {
        Specification<WatchingRace> spec = buildSpecification(watchingRace);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<WatchingRace> buildSpecification(final WatchingRace w) {
        return new Specification<WatchingRace>() {
            @Override
            public Predicate toPredicate(Root<WatchingRace> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (w.getName() != null) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + w.getName() + "%"));
                }

                if(w.getCity().getCityId() != null){
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class),w.getCity().getCityId()));
                }
                if(w.getProvince().getProvinceId() != null){
                    list.add(cb.equal(root.get("province").get("provinceId").as(Long.class),w.getProvince().getProvinceId()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}
