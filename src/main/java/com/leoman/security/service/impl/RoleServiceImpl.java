package com.leoman.security.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.security.dao.RoleDao;
import com.leoman.security.entity.Role;
import com.leoman.security.service.RoleService;
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
 * Created by Administrator on 2016/6/13.
 */
@Service
public class RoleServiceImpl extends GenericManagerImpl<Role,RoleDao> implements RoleService{

    @Autowired
    private RoleDao dao;

    @Override
    public Page<Role> findAll(Role role, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Role> spec = buildSpecification(role);
        Page<Role> page = dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<Role> list = page.getContent();
        for(Role r : list){
            r.setNum(dao.adminNum(r.getId()));
        }
        return page;
    }

}
