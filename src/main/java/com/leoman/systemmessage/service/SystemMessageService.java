package com.leoman.systemmessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.systemmessage.entity.SystemMessage;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface SystemMessageService extends GenericManager<SystemMessage>{

    public Page<SystemMessage> findAll(SystemMessage systemMessage, Integer currentPage, Integer pageSize) throws Exception;
}
