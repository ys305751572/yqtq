package com.leoman.girl.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girl.dao.GirlImageDao;
import com.leoman.girl.entity.GirlImage;
import com.leoman.girl.service.GirlImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/7.
 */
@Service
public class GirlImageServiceImpl extends GenericManagerImpl<GirlImage,GirlImageDao> implements GirlImageService {
    @Autowired
    private GirlImageDao dao;

}
