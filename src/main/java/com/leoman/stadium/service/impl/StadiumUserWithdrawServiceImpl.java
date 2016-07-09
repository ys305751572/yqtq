package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumUserWithdrawDao;
import com.leoman.stadium.entity.StadiumUserWithdraw;
import com.leoman.stadium.service.StadiumUserWithdrawService;
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
import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
@Service
public class StadiumUserWithdrawServiceImpl extends GenericManagerImpl<StadiumUserWithdraw,StadiumUserWithdrawDao> implements StadiumUserWithdrawService{

    @Autowired
    private StadiumUserWithdrawDao dao;

    @Override
    public Page<StadiumUserWithdraw> findAll(String details,StadiumUserWithdraw stadiumUserWithdraw, Integer currentPage, Integer pageSize) throws Exception {
        Specification<StadiumUserWithdraw> spec = buildSpecification(details,stadiumUserWithdraw);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }


    public Specification<StadiumUserWithdraw> buildSpecification(final String details,final StadiumUserWithdraw s) {
        return new Specification<StadiumUserWithdraw>() {
            @Override
            public Predicate toPredicate(Root<StadiumUserWithdraw> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(s.getStadiumUser().getUsername() != null) {
                    list.add(cb.like(root.get("stadiumUser").get("username").as(String.class), "%" + s.getStadiumUser().getUsername() +"%" ));
                }
                if(s.getStatus() != null){
                    list.add(cb.equal(root.get("status").as(Integer.class),s.getStatus()));
                }
                if (StringUtils.isNotBlank(details) && "1".equals(details)) {
                    list.add(cb.ge(root.get("createDate").as(Long.class), TestUtil.getTimesmorning()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

}
