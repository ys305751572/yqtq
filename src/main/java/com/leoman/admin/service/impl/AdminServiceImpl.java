package com.leoman.admin.service.impl;

import com.leoman.admin.dao.AdminDao;
import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.utils.TestUtil;
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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class AdminServiceImpl extends GenericManagerImpl<Admin,AdminDao> implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public Admin findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public Page<Admin> findAll(List roleIds,Admin admin, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Admin> spec = buildSpecification(roleIds,admin);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<Admin> buildSpecification(final List roleIds,final Admin u) {
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(!u.getUsername().isEmpty()){
                    list.add(cb.like(root.get("username").as(String.class),"%"+u.getUsername()+"%"));
                }
                if(roleIds.size()>0 && !roleIds.isEmpty()) {
                    Iterator iterator = roleIds.iterator();
                    CriteriaBuilder.In in = cb.in(root.get("id"));
                    while (iterator.hasNext()) {
                        in.value(iterator.next());
                    }
                    list.add(in);
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
