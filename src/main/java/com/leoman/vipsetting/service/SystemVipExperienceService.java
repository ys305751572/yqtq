package com.leoman.vipsetting.service;

import com.leoman.common.service.GenericManager;
import com.leoman.vipsetting.entity.SystemVipExperience;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface SystemVipExperienceService extends GenericManager<SystemVipExperience>{

    public List<SystemVipExperience> experienceList(Long systemVipId, Integer action);
}
