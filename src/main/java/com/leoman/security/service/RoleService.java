package com.leoman.security.service;

import com.leoman.common.service.GenericManager;
import com.leoman.security.entity.Role;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/6/13.
 */
public interface RoleService extends GenericManager<Role>{

    public Page<Role> findAll(Role role, Integer currentPage, Integer pageSize) throws Exception ;
}
