package com.leoman.information.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.information.dao.InformationDao;
import com.leoman.information.entity.Information;
import com.leoman.information.service.InformationService;
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
 * Created by Administrator on 2016/6/8 0008 下午 4:35.
 */
@Service
public class InformationServiceImpl extends GenericManagerImpl<Information, InformationDao> implements InformationService {

    @Autowired
    private InformationDao informationDao;

    @Override
    public Information findById(Long id) {
        return informationDao.findById(id);
    }

    @Override
    public Page<Information> findPage(final String introduction, int pageNum, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id");

        Page<Information> page = informationDao.findAll(new Specification<Information>() {
            @Override
            public Predicate toPredicate(Root<Information> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate result = null;
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (StringUtils.isNotEmpty(introduction)) {
                    Predicate pre = cb.like(root.get("introduction").as(String.class), "%" + introduction + "%");
                    predicateList.add(pre);
                }
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