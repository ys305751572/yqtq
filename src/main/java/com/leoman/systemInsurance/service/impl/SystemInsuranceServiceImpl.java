package com.leoman.systemInsurance.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.systemInsurance.dao.SystemInsuranceDao;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.systemInsurance.service.SystemInsuranceService;
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
 * Created by Administrator on 2016/6/1.
 */
@Service
public class SystemInsuranceServiceImpl extends GenericManagerImpl<SystemInsurance, SystemInsuranceDao> implements SystemInsuranceService {

    @Autowired
    private SystemInsuranceDao systemInsuranceDao;

    @Override
    public Page<SystemInsurance> findPage(int pageNum, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<SystemInsurance> page = systemInsuranceDao.findAll(new Specification<SystemInsurance>() {
            @Override
            public Predicate toPredicate(Root<SystemInsurance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if (predicateList.size() > 0) {
                    result = cb.and(predicateList.toArray(new Predicate[]{}));
                }

                if (result != null) {
                    query.where(result);
                }
                return query.getGroupRestriction();
            }

        }, pageRequest);

        return page;
    }
}
