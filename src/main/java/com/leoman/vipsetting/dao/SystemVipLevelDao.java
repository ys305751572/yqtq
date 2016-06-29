package com.leoman.vipsetting.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.vipsetting.entity.SystemVipLevel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface SystemVipLevelDao extends IBaseJpaRepository<SystemVipLevel>{

    @Query("SELECT a FROM SystemVipLevel a ORDER BY a.level")
    public List<SystemVipLevel> OrderByLevel();
}
