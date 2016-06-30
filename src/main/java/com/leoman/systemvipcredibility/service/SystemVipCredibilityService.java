package com.leoman.systemvipcredibility.service;

import com.leoman.common.service.GenericManager;
import com.leoman.systemvipcredibility.entity.SystemVipCredibility;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface SystemVipCredibilityService extends GenericManager<SystemVipCredibility>{

    public List<SystemVipCredibility> credibilityList(Long systemVipId, Integer action);

}
