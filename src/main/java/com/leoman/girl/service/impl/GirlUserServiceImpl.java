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
public class GirlUserServiceImpl extends GenericManagerImpl<GirlUser,GirlUserDao> implements GirlUserService {

    @Autowired
    private GirlUserDao dao;

    @Override
    public Page<GirlUser> findAll(GirlUser girlUser, Integer currentPage, Integer pageSize) throws Exception {
        Specification<GirlUser> spec = buildSpecification(girlUser);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<GirlUser> buildSpecification(final GirlUser g) {
        return new Specification<GirlUser>() {
            @Override
            public Predicate toPredicate(Root<GirlUser> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (g.getGirl().getCity().getCityId() != null) {
                    list.add(cb.equal(root.get("girl").get("city").get("cityId").as(Long.class), g.getGirl().getCity().getCityId()));
                }

                if (g.getGirl().getName() != null) {
                    list.add(cb.like(root.get("girl").get("name").as(String.class), "%" + g.getGirl().getName() + "%"));
                }

                if(g.getUser().getNickName() != null){
                    list.add(cb.like(root.get("user").get("nickName").as(String.class), "%" + g.getUser().getNickName() + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
