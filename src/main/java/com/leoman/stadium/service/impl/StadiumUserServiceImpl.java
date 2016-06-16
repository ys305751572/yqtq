package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumUserDao;
import com.leoman.stadium.entity.StadiumUser;
import com.leoman.stadium.service.StadiumUserService;
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
 * Created by Administrator on 2016/6/12.
 */
@Service
public class StadiumUserServiceImpl extends GenericManagerImpl<StadiumUser,StadiumUserDao> implements StadiumUserService{

    @Autowired
    private StadiumUserDao dao;


    @Override
    public Page<StadiumUser> findAll(StadiumUser stadiumUser, Integer currentPage, Integer pageSize) throws Exception {
        Specification<StadiumUser> spec = buildSpecification(stadiumUser);
        Page<StadiumUser> page = dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<StadiumUser> list = page.getContent();
        for(StadiumUser s : list){
            s.setStadiumNum(this.stadiumNum(s.getId()));
            s.setToDaySumPrice(this.toDaySumPrice(s.getId()));
        }
        return page;
    }

    @Override
    public Integer stadiumNum(Long id) {
        return dao.stadiumNum(id);
    }

    @Override
    public Double toDaySumPrice(Long id) {
        return dao.toDaySumPrice(id);
    }

    public Specification<StadiumUser> buildSpecification(final StadiumUser s) {
        return new Specification<StadiumUser>() {
            @Override
            public Predicate toPredicate(Root<StadiumUser> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(s.getUsername() != null) {
                    list.add(cb.like(root.get("username").as(String.class), "%" + s.getUsername() +"%" ));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
