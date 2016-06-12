package com.leoman.reserve.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.reserve.dao.ReserveDao;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.service.ReserveService;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systemInsurance.entity.SystemInsurance;
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
public class ReserveServiceImpl extends GenericManagerImpl<Reserve,ReserveDao> implements ReserveService {

    @Autowired
    private ReserveDao dao;

    @Override
    public Page<Reserve> findPage(final Reserve reserve, int pagenum, int pagesize) {
        Specification<Reserve> spec = new Specification<Reserve>() {
            @Override
            public Predicate toPredicate(Root<Reserve> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (reserve.getStadium().getCity().getCityId() != null) {
                    list.add(criteriaBuilder.equal(root.get("stadium").get("city").get("cityId").as(Long.class), reserve.getStadium().getCity().getCityId()));
                }
                if (reserve.getStadium().getId() != null) {
                    list.add(criteriaBuilder.equal(root.get("stadium").get("id").as(Long.class), reserve.getStadium().getId()));
                }
                if (reserve.getMatchType() != null) {
                    list.add(criteriaBuilder.equal(root.get("matchType").as(Integer.class), reserve.getMatchType()));
                }
                if (reserve.getPayment() != null) {
                    list.add(criteriaBuilder.equal(root.get("payment").as(Integer.class), reserve.getPayment()));
                }
                if (reserve.getSystemInsurance().getId() != null) {
                    list.add(criteriaBuilder.equal(root.get("systemInsurance").get("id").as(String.class), reserve.getSystemInsurance().getId()));
                }
                if (reserve.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), reserve.getStatus()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<Reserve> page = dao.findAll(spec,new PageRequest(pagenum - 1,pagesize, Sort.Direction.DESC,"id"));
        List<Reserve> reserves = page.getContent();
        for(Reserve r : reserves){
            r.setNum(this.findNum(r.getId()));
        }
        return page;
    }

    @Override
    public Integer findNum(Long reserveId) {
        return dao.findNum(reserveId);
    }

}
