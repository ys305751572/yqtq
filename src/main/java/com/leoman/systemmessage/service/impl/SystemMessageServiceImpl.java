package com.leoman.systemmessage.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.systemmessage.dao.SystemMessageDao;
import com.leoman.systemmessage.entity.SystemMessage;
import com.leoman.systemmessage.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/30.
 */
@Service
public class SystemMessageServiceImpl extends GenericManagerImpl<SystemMessage,SystemMessageDao> implements SystemMessageService{

    @Autowired
    private SystemMessageDao dao;


    @Override
    public Page<SystemMessage> findAll(SystemMessage systemMessage, Integer currentPage, Integer pageSize) throws Exception {
        Specification<SystemMessage> spec = buildSpecification(systemMessage);
        return dao.findAll(spec, new PageRequest(currentPage-1, pageSize, Sort.Direction.DESC, "id"));
    }

}
