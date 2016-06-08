package com.leoman.activity.dao;

import com.leoman.activity.entity.Activity;
import com.leoman.common.dao.IBaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/8 0008 下午 4:32.
 */
public interface ActivityDao extends IBaseJpaRepository<Activity>,JpaSpecificationExecutor<Activity> {

    public Activity findById(Long id);
}