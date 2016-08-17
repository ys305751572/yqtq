package com.leoman.sitemanage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.sitemanage.dao.SiteManageDao;
import com.leoman.sitemanage.entity.SiteManage;
import com.leoman.sitemanage.service.SiteManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/15.
 */
@Service
public class SiteManageServiceImpl extends GenericManagerImpl<SiteManage,SiteManageDao> implements SiteManageService{

    @Autowired
    private SiteManageDao dao;


    @Override
    public SiteManage findUpdate(Long siteId, Long start, Long end) {
        return dao.findUpdate(siteId,start,end);
    }

}
