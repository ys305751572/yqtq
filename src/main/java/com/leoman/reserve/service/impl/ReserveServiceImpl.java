package com.leoman.reserve.service.impl;

import com.leoman.SystemInsurance.controller.SystemInsuranceController;
import com.leoman.SystemInsurance.entity.SystemInsurance;
import com.leoman.reserve.dao.ReserveDao;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
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
 * Created by Administrator on 2016/5/24.
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveDao dao;

    @Override
    public Page<Reserve> findPage(final Reserve reserve, int pagenum, int pagesize) {
        Specification<Reserve> spec = new Specification<Reserve>() {
            @Override
            public Predicate toPredicate(Root<Reserve> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (reserve.getCityId() != null) {
                    list.add(criteriaBuilder.equal(root.get("cityId").as(Long.class), reserve.getCityId()));
                }
                if (reserve.getStadiumId() != null) {
                    list.add(criteriaBuilder.equal(root.get("stadiumId").as(Long.class), reserve.getStadiumId()));
                }
                if (reserve.getMatchType() != null) {
                    list.add(criteriaBuilder.equal(root.get("matchType").as(Integer.class), reserve.getMatchType()));
                }
                if (reserve.getPayment() != null) {
                    list.add(criteriaBuilder.equal(root.get("payment").as(Integer.class), reserve.getPayment()));
                }
                if (reserve.getName() != null) {
                    list.add(criteriaBuilder.equal(root.get("name").as(Long.class), reserve.getName()));
                }
                if (reserve.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), reserve.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Reserve> findAll() {
        return null;
    }

    @Override
    public Page<Reserve> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Reserve> find(int pageNum) {
        return null;
    }

    @Override
    public Reserve getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Reserve deleteById(Long id) {
        Reserve reserve = dao.findOne(id);
        dao.delete(reserve);
        return null;
    }

    @Override
    public Reserve create(Reserve reserve) {
        return dao.save(reserve);
    }

    @Override
    public Reserve update(Reserve reserve) {
        return dao.save(reserve);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
