package com.leoman.girlServiceMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.girlServiceMessage.entity.GirlServiceMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface GirlServiceMessageDao extends IBaseJpaRepository<GirlServiceMessage>,JpaSpecificationExecutor<GirlServiceMessage> {

    public GirlServiceMessage findById(Long id);
}

