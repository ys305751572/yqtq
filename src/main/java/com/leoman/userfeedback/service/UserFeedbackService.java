package com.leoman.userfeedback.service;

import com.leoman.common.service.GenericManager;
import com.leoman.userfeedback.entity.UserFeedback;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface UserFeedbackService extends GenericManager<UserFeedback>{

    public Page<UserFeedback> findAll(UserFeedback userFeedback, Integer currentPage, Integer pageSize) throws Exception;

}
