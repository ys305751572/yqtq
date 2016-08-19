package com.leoman.sitemanage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.sitemanage.dao.SiteTimeDao;
import com.leoman.sitemanage.entity.SiteTime;
import com.leoman.sitemanage.service.SiteTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/19.
 */
@Service
public class SiteTimeServiceImpl extends GenericManagerImpl<SiteTime,SiteTimeDao> implements SiteTimeService{

    @Autowired
    private SiteTimeDao dao;

}
