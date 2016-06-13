package com.leoman.orderballMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.orderballMessage.entity.OrderballMessage;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface OrderballMessageService extends GenericManager<OrderballMessage>  {

    public OrderballMessage findById(Long id);
}
