package com.leoman.vipsetting.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.vipsetting.dao.SystemVipExperienceDao;
import com.leoman.vipsetting.entity.SystemVipExperience;
import com.leoman.vipsetting.service.SystemVipExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service
public class SystemVipExperienceServiceImpl extends GenericManagerImpl<SystemVipExperience,SystemVipExperienceDao> implements SystemVipExperienceService{

    @Autowired
    private SystemVipExperienceDao dao;

    @Override
    public List<SystemVipExperience> experienceList(Long systemVipId, Integer action) {
        return dao.experienceList(systemVipId,action);
    }
}
