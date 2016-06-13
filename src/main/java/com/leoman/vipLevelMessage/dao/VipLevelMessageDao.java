package com.leoman.vipLevelMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.vipLevelMessage.entity.VipLevelMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface VipLevelMessageDao extends IBaseJpaRepository<VipLevelMessage>,JpaSpecificationExecutor<VipLevelMessage> {

    public VipLevelMessage findById(Long id);
}

