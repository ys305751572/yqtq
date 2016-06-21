package com.leoman.city.service.impl;

import com.leoman.city.dao.ProvinceDao;
import com.leoman.city.entity.Province;
import com.leoman.city.service.ProvinceService;
import com.leoman.common.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/21.
 */
@Service
public class ProvinceServiceImpl extends GenericManagerImpl<Province,ProvinceDao> implements ProvinceService{

    @Autowired
    private ProvinceDao dao;

}
