package com.leoman.user.service;

import com.leoman.common.service.GenericManager;
import com.leoman.user.entity.UserVip;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/13.
 */
public interface UserVipService extends GenericManager<UserVip>{

    public Page<UserVip> findAll(String details,UserVip userVip, Integer currentPage, Integer pageSize) throws Exception;

}
