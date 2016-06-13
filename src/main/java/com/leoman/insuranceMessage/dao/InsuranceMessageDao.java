package com.leoman.insuranceMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.insuranceMessage.entity.InsuranceMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface InsuranceMessageDao extends IBaseJpaRepository<InsuranceMessage>,JpaSpecificationExecutor<InsuranceMessage> {

    public InsuranceMessage findById(Long id);
}

