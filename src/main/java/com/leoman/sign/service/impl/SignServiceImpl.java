package com.leoman.sign.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.sign.dao.SignDao;
import com.leoman.sign.entity.Sign;
import com.leoman.sign.service.SignService;
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
 * Created by Administrator on 2016/5/19.
 */
@Service
public class SignServiceImpl extends GenericManagerImpl<Sign, SignDao> implements SignService {

    @Autowired
    private SignDao dao;

    @Override
    public Page<Sign> findPage(Integer pagenum, Integer pagesize) {

        Specification<Sign> spec = new Specification<Sign>() {
            @Override
            public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum-1, pagesize, Sort.Direction.DESC, "id"));
    }
}
