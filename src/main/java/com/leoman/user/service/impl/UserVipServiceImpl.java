package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.user.dao.UserVipDao;
import com.leoman.user.entity.UserVip;
import com.leoman.user.service.UserVipService;
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
 * Created by Administrator on 2016/6/13.
 */
@Service
public class UserVipServiceImpl extends GenericManagerImpl<UserVip,UserVipDao> implements UserVipService{

    @Autowired
    private UserVipDao dao;

    @Override
    public Page<UserVip> findAll(String details,UserVip userVip, Integer currentPage, Integer pageSize) throws Exception {
        Specification<UserVip> spec = buildSpecification(details,userVip);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

    public Specification<UserVip> buildSpecification(final String details,final UserVip u) {
        return new Specification<UserVip>() {
            @Override
            public Predicate toPredicate(Root<UserVip> root, CriteriaQuery<?> cq,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(u.getUser().getMobile() != null) {
                    list.add(cb.like(root.get("user").get("mobile").as(String.class), "%" + (u.getUser().getMobile() + "%")));
                }

                if(u.getUser().getNickName() != null) {
                    list.add(cb.like(root.get("user").get("nickName").as(String.class), "%" + (u.getUser().getNickName() + "%")));
                }

                if(u.getUser().getVipLevel() != null) {
                    list.add(cb.equal(root.get("user").get("vipLevel").as(String.class),(u.getUser().getVipLevel())));
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
