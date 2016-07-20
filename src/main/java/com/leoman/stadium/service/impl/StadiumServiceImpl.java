package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumDao;
import com.leoman.stadium.entity.Stadium;
import com.leoman.stadium.entity.StadiumSub;
import com.leoman.stadium.service.StadiumService;
import com.leoman.user.entity.User;
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
 * Created by Administrator on 2016/5/31.
 */
@Service
public class StadiumServiceImpl extends GenericManagerImpl<Stadium, StadiumDao> implements StadiumService {
    @Autowired
    private StadiumDao dao;

    @Override
    public List<Stadium> findAll() {
        return dao.findAll();
    }

    @Override
    public Stadium findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Page<Stadium> findAll(Stadium stadium, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Stadium> spec = buildSpecification(stadium);
        Page<Stadium> page =  dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<Stadium> list = page.getContent();
        for(Stadium s : list){
            s.setAvailableStadiumNum(this.availableStadiumNum(s.getId()));
            s.setStadiumNum(this.stadiumNum(s.getId()));
            s.setAccumulatedAmount(this.accumulatedAmount(s.getId()));
        }
        return page;
    }

    @Override
    public List<User> findByNickName(Long id) {
        return dao.findByNickName(id);
    }

    public Integer availableStadiumNum(Long id){
        return dao.availableStadiumNum(id);
    }

    public Integer stadiumNum(Long id){
        return dao.stadiumNum(id);
    }

    public Integer accumulatedAmount(Long id){
        return dao.accumulatedAmount(id);
    }

    public Specification<Stadium> buildSpecification(final Stadium s) {
        return new Specification<Stadium>() {
            @Override
            public Predicate toPredicate(Root<Stadium> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(s.getName() != null) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + s.getName() +"%" ));
                }
                if(s.getStadiumUserId() != null) {
                    list.add(cb.equal(root.get("stadiumUserId").as(Long.class), s.getStadiumUserId() ));
                }
                if(s.getProvince()!= null && s.getProvince().getProvinceId() != null) {
                    list.add(cb.equal(root.get("province").get("provinceId").as(Long.class), s.getProvince().getProvinceId()));
                }
                if(s.getCity()!= null && s.getCity().getCityId() != null) {
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class), s.getCity().getCityId()));
                }
                if(s.getType() != null){
                    list.add(cb.equal(root.get("type").as(Integer.class),s.getType()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
