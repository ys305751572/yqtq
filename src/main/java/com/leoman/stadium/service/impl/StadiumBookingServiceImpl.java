package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumBookingDao;
import com.leoman.stadium.entity.StadiumBooking;
import com.leoman.stadium.service.StadiumBookingService;
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
 * Created by Administrator on 2016/6/16.
 */
@Service
public class StadiumBookingServiceImpl extends GenericManagerImpl<StadiumBooking,StadiumBookingDao> implements StadiumBookingService{

    @Autowired
    private StadiumBookingDao dao;

    @Override
    public Page<StadiumBooking> findAll(StadiumBooking stadiumBooking, Integer currentPage, Integer pageSize) throws Exception {
        Specification<StadiumBooking> spec = buildSpecification(stadiumBooking);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<StadiumBooking> buildSpecification(final StadiumBooking s) {
        return new Specification<StadiumBooking>() {
            @Override
            public Predicate toPredicate(Root<StadiumBooking> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(s.getUser().getNickName() != null) {
                    list.add(cb.like(root.get("user").get("nickName").as(String.class), "%" + s.getUser().getNickName() +"%" ));
                }
                if(s.getCity().getCityId() != null) {
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class),s.getCity().getCityId()));
                }
                if(s.getStadium().getName() != null) {
                    list.add(cb.like(root.get("stadium").get("name").as(String.class),"%" + s.getStadium().getName()+"%"));
                }
                if(s.getBookTime() != null) {
                    list.add(cb.equal(root.get("bookTime").as(Integer.class),s.getBookTime()));
                }
                if(s.getType() != null) {
                    list.add(cb.equal(root.get("type").as(Integer.class),s.getType()));
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}