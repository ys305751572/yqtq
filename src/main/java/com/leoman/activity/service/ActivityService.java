package com.leoman.activity.service;


import com.leoman.activity.entity.Activity;
import com.leoman.common.service.GenericManager;
import com.leoman.common.service.ICommonService;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/8 0008 下午 4:34.
 */
public interface ActivityService extends GenericManager<Activity> {

    public Page<Activity> findPage(String introduction, int pageNum, int pageSize);

    public Activity findById(Long id);
}