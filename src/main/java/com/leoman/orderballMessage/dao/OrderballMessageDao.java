package com.leoman.orderballMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.orderballMessage.entity.OrderballMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface OrderballMessageDao extends IBaseJpaRepository<OrderballMessage>,JpaSpecificationExecutor<OrderballMessage> {

    public OrderballMessage findById(Long id);
}

