package com.leoman.systembanner.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.systembanner.dao.SystemBannerDao;
import com.leoman.systembanner.entity.SystemBanner;
import com.leoman.systembanner.service.SystemBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service
public class SystemBannerServiceImpl extends GenericManagerImpl<SystemBanner,SystemBannerDao> implements SystemBannerService{

    @Autowired
    private SystemBannerDao dao;

}
