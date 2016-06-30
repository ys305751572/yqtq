package com.leoman.vipsetting.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.vipsetting.entity.SystemVipExperience;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface SystemVipExperienceDao extends IBaseJpaRepository<SystemVipExperience>{

    @Query("SELECT a FROM SystemVipExperience a WHERE a.systemVipId=?1 AND a.action=?2")
    public List<SystemVipExperience> experienceList(Long systemVipId,Integer action);

}
