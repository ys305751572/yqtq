package com.leoman.user.service.impl;

import com.leoman.user.dao.UserDao;
import com.leoman.user.entity.User;
import com.leoman.user.service.UserService;
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
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;


    @Override
    public User findByUserId(Long userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public Page<User> findPage(final User user, final Integer sortId, int pagenum, int pagesize) {
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
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum - 1, pagesize, isDesc ? Sort.Direction.DESC : Sort.Direction.ASC, property));
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<User> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<User> find(int pageNum) {
        return null;
    }

    @Override
    public User getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public User deleteById(Long id) {
        User user = dao.findOne(id);
        dao.delete(user);
        return null;
    }

    @Override
    public User create(User user) {
        return dao.save(user);
    }

    @Override
    public User update(User user) {
        return dao.save(user);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

}
