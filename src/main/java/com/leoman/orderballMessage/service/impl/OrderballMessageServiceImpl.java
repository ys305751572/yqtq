package com.leoman.orderballMessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.orderballMessage.dao.OrderballMessageDao;
import com.leoman.orderballMessage.entity.OrderballMessage;
import com.leoman.orderballMessage.service.OrderballMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class OrderballMessageServiceImpl extends GenericManagerImpl<OrderballMessage, OrderballMessageDao> implements OrderballMessageService {

    @Autowired
    private OrderballMessageDao orderballMessageDao;

    @Override
    public OrderballMessage findById(Long id) {
        return orderballMessageDao.findById(id);
    }
}
