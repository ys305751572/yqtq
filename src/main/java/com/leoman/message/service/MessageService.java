package com.leoman.message.service;

import com.leoman.common.service.GenericManager;
import com.leoman.message.entity.Message;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface MessageService extends GenericManager<Message>  {

    public Message getByType(Integer type);
}
