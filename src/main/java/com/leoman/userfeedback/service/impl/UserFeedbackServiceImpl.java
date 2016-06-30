package com.leoman.userfeedback.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.userfeedback.dao.UserFeedbackDao;
import com.leoman.userfeedback.entity.UserFeedback;
import com.leoman.userfeedback.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/30.
 */
@Service
public class UserFeedbackServiceImpl extends GenericManagerImpl<UserFeedback,UserFeedbackDao> implements UserFeedbackService{

    @Autowired
    private UserFeedbackDao dao;

    @Override
    public Page<UserFeedback> findAll(UserFeedback userFeedback, Integer currentPage, Integer pageSize) throws Exception {
        Specification<UserFeedback> spec = buildSpecification(userFeedback);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }
}
