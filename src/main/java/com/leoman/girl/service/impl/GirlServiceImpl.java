package com.leoman.girl.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlDao;
import com.leoman.girl.entity.Girl;
import com.leoman.girl.entity.GirlUser;
import com.leoman.girl.service.GirlService;
import com.leoman.utils.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
@Service
public class GirlServiceImpl extends GenericManagerImpl<Girl,GirlDao> implements GirlService{

    @Autowired
    private GirlDao dao;

    @Override
    public Page<Girl> findAll(Integer appointment,Girl girl, Integer currentPage, Integer pageSize) throws Exception {
        Specification<Girl> spec = buildSpecification(appointment,girl);
        Page<Girl> page = dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
        List<Girl> girls = page.getContent();
        for(Girl g : girls){
            g.setGuSize(this.findSize(g.getId()));
        }
        return page;
    }

    @Override
    public Integer findSize(Long id) {
        return dao.findSize(id);
    }

    public Specification<Girl> buildSpecification(final Integer appointment,final Girl g) {
        return new Specification<Girl>() {
            @Override
            public Predicate toPredicate(Root<Girl> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(!g.getName().isEmpty()) {
                    list.add(cb.like(root.get("name").as(String.class), "%" + g.getName() + "%"));
                }
                if(g.getCity().getCityId() != null){
                    list.add(cb.equal(root.get("city").get("cityId").as(Long.class), g.getCity().getCityId() ));
                }
                if(g.getStatus() != null){
                    list.add(cb.equal(root.get("status").as(Integer.class), g.getStatus() ));
                }
                if(appointment !=null){
                    if(appointment==1){
                        List ids = dao.appointmentId(TestUtil.getTimesmorning(),TestUtil.getTimesnight());
                        if(ids.size()>0 && !ids.isEmpty()) {
                            Iterator iterator = ids.iterator();
                            CriteriaBuilder.In in = cb.in(root.get("id"));
                            while (iterator.hasNext()) {
                                in.value(iterator.next());
                            }
                                list.add(in);
                        }else {
                            list.add(cb.equal(root.get("id").as(Long.class), 0));
                        }
                    }else {
                            //今天没有预约的
                        List ids = dao.asd(TestUtil.getTimesmorning(),TestUtil.getTimesnight());
                        if(ids.size()>0 && !ids.isEmpty()) {
                            Iterator iterator = ids.iterator();
                            CriteriaBuilder.In in = cb.in(root.get("id"));
                            while (iterator.hasNext()) {
                                in.value(iterator.next());
                            }
                            list.add(in);
                        }
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }
}
