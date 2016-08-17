package com.leoman.sitemanage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.sitemanage.entity.SiteManage;

/**
 * Created by Administrator on 2016/8/15.
 */
public interface SiteManageService extends GenericManager<SiteManage>{

    public SiteManage findUpdate(Long siteId,Long start,Long end);

}
