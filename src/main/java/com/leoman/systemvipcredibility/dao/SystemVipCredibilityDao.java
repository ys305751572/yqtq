package com.leoman.systemvipcredibility.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.systemvipcredibility.entity.SystemVipCredibility;
import com.leoman.vipsetting.entity.SystemVipExperience;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface SystemVipCredibilityDao extends IBaseJpaRepository<SystemVipCredibility>{

    @Query("SELECT a FROM SystemVipCredibility a WHERE a.systemVipId=?1 AND a.action=?2")
    public List<SystemVipCredibility> credibilityList(Long systemVipId, Integer action);

}
