package com.leoman.security.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.security.entity.Role;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/13.
 */
public interface RoleDao extends IBaseJpaRepository<Role>{

    @Query("SELECT COUNT(a) FROM UserRole a WHERE a.role.id = ?1")
    public Integer adminNum(Long roleId);

}
