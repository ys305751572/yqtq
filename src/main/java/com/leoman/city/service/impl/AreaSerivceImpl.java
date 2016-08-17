package com.leoman.city.service.impl;

import com.leoman.city.dao.AreaDao;
import com.leoman.city.entity.Area;
import com.leoman.city.service.AreaService;
import com.leoman.common.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/11.
 */
@Service
public class AreaSerivceImpl extends GenericManagerImpl<Area,AreaDao> implements AreaService{

    @Autowired
    private AreaDao dao;

}


