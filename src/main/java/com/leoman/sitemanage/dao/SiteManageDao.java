package com.leoman.sitemanage.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.sitemanage.entity.SiteManage;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public interface SiteManageDao extends IBaseJpaRepository<SiteManage>{

    @Query("SELECT a FROM SiteManage a WHERE a.siteId = ?1 AND a.startDate =?2 AND a.endDate = ?3")
    public SiteManage findUpdate(Long siteId,Long start,Long end);

}
