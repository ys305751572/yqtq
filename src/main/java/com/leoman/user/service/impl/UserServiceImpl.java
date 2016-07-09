package com.leoman.user.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.index.service.IndexService;
import com.leoman.user.dao.UserDao;
import com.leoman.user.entity.User;
import com.leoman.user.service.UserService;
import com.leoman.utils.TestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
@Service
public class UserServiceImpl extends GenericManagerImpl<User,UserDao> implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private IndexService indexService;


    @Override
    public User findByUserId(Long userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public Page<User> findPage(final String details,final User user, final Integer sortId, int pagenum, int pagesize) {
        boolean isDesc = false;
        String property = "id";
        Specification<User> spec = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                Predicate result = null;
                if (StringUtils.isNotBlank(user.getMobile())) {
                    list.add(criteriaBuilder.like(root.get("mobile").as(String.class), '%' + user.getMobile() + '%'));
                }
                if (StringUtils.isNotBlank(user.getNickName())) {
                    list.add(criteriaBuilder.like(root.get("nickName").as(String.class), '%' + user.getNickName() + '%'));
                }
                if (user.getStatus() != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), user.getStatus()));
                }
                if (user.getVipLevel() != null && user.getVipLevel()==0) {
                    list.add(criteriaBuilder.equal(root.get("vipLevel").as(Integer.class), user.getVipLevel()));
                }
                if (user.getVipLevel() != null && user.getVipLevel()==1) {
                    list.add(criteriaBuilder.notEqual(root.get("vipLevel").as(Integer.class), 0));
                }
                if (StringUtils.isNotBlank(details) && "1".equals(details)) {
                    list.add(criteriaBuilder.ge(root.get("createDate").as(Long.class), TestUtil.getTimesmorning()));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Page<User> page = dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, isDesc ? Sort.Direction.DESC : Sort.Direction.ASC, property));
        List<User> userList = page.getContent();
        for(User u : userList){
            u.setSumPrice(dao.SumPrice(u.getId()));
        }
        return page;
    }

}
