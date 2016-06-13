package com.leoman.serviceMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.serviceMessage.entity.ServiceMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface ServiceMessageDao extends IBaseJpaRepository<ServiceMessage>,JpaSpecificationExecutor<ServiceMessage> {

    public ServiceMessage findById(Long id);
}

