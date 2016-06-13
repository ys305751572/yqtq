package com.leoman.credibilityMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.credibilityMessage.dao.CredibilityMessageDao;
import com.leoman.credibilityMessage.entity.CredibilityMessage;
import com.leoman.credibilityMessage.service.CredibilityMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class CredibilityMessageServiceImpl extends GenericManagerImpl<CredibilityMessage, CredibilityMessageDao> implements CredibilityMessageService {

    @Autowired
    private CredibilityMessageDao credibilityMessageDao;

}
