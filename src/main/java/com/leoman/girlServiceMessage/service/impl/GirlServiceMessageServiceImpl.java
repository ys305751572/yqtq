package com.leoman.girlServiceMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.girlServiceMessage.dao.GirlServiceMessageDao;
import com.leoman.girlServiceMessage.entity.GirlServiceMessage;
import com.leoman.girlServiceMessage.service.GirlServiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class GirlServiceMessageServiceImpl extends GenericManagerImpl<GirlServiceMessage, GirlServiceMessageDao> implements GirlServiceMessageService {

    @Autowired
    private GirlServiceMessageDao girlServiceMessageDao;

    @Override
    public GirlServiceMessage findById(Long id) {
        return girlServiceMessageDao.findById(id);
    }
}
