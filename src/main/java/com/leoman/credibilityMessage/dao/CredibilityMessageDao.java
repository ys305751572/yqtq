package com.leoman.credibilityMessage.dao;


import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.credibilityMessage.entity.CredibilityMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/13 0013 上午 11:26.
 */
public interface CredibilityMessageDao extends IBaseJpaRepository<CredibilityMessage>,JpaSpecificationExecutor<CredibilityMessage> {

}

