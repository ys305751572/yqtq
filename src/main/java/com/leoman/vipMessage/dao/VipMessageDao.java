package com.leoman.vipMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.vipMessage.entity.VipMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface VipMessageDao extends IBaseJpaRepository<VipMessage>,JpaSpecificationExecutor<VipMessage> {

    public VipMessage findById(Long id);
}

