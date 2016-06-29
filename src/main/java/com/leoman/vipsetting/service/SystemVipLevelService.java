package com.leoman.vipsetting.service;

import com.leoman.common.service.GenericManager;
import com.leoman.vipsetting.entity.SystemVipLevel;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface SystemVipLevelService extends GenericManager<SystemVipLevel>{

    public List<SystemVipLevel> OrderByLevel();

}
