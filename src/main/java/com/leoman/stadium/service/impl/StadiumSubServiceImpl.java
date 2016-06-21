package com.leoman.stadium.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.stadium.dao.StadiumSubDao;
import com.leoman.stadium.entity.StadiumSub;
import com.leoman.stadium.service.StadiumSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/21.
 */
@Service
public class StadiumSubServiceImpl extends GenericManagerImpl<StadiumSub,StadiumSubDao> implements StadiumSubService {

    @Autowired
    private StadiumSubDao dao;


}
