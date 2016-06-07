package com.leoman.city.service.impl;

import com.alibaba.druid.filter.AutoLoad;
import com.leoman.city.dao.CityDao;
import com.leoman.city.entity.City;
import com.leoman.city.service.CityService;
import com.leoman.common.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */

@Service
public class CityServiceImpl extends GenericManagerImpl<City,CityDao> implements CityService{


    @Autowired
    private CityDao dao;
}
