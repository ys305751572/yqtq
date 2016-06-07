package com.leoman.girl.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.service.GirlService;
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
 * Created by Administrator on 2016/6/7.
 */
@Service
public class GirlServiceImpl extends GenericManagerImpl<Girl,GirlDao> implements GirlService{

    @Autowired
    private GirlDao dao;

    @Override
    public Page<Girl> findAll(Girl girl, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Girl> spec = buildSpecification(girl);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<Girl> buildSpecification(final Girl g) {
        return new Specification<Girl>() {
            @Override
            public Predicate toPredicate(Root<Girl> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(g.getName() != null) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + g.getName() + "%"));
                }
                if(g.getCity().getCityId() != null){
                    list.add(cb.equal(root.get("city").get("cityId").as(Integer.class), g.getCity().getCityId() ));
                }
                if(g.getStatus() != null){
                    list.add(cb.equal(root.get("status").as(Integer.class), g.getStatus() ));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
