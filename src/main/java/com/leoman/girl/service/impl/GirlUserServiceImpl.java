package com.leoman.girl.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlUserDao;
import com.leoman.girl.entity.GirlUser;
import com.leoman.girl.service.GirlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2016/6/7.
 */
@Service
public class GirlUserServiceImpl extends GenericManagerImpl<GirlUser,GirlUserDao> implements GirlUserService {

    @Autowired
    private GirlUserDao dao;

    @Override
    public Page<GirlUser> findAll(GirlUser girlUser, Integer currentPage, Integer pageSize) throws Exception {
        Specification<GirlUser> spec = buildSpecification(girlUser);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }
}
