package com.leoman.message.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.message.dao.MessageDao;
import com.leoman.message.entity.Message;
import com.leoman.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class MessageServiceImpl extends GenericManagerImpl<Message, MessageDao> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public Message getByType(Integer type) {
        return messageDao.getByType(type);
    }
}
