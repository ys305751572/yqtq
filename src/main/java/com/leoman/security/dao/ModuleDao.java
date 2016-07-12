package com.leoman.security.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.security.entity.Module;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public interface ModuleDao extends IBaseJpaRepository<Module>{

    @Query("SELECT a FROM Module a WHERE a.parent IS NULL")
    public List<Module> mainModule();
    @Query("SELECT a FROM Module a WHERE a.parent IS NOT NULL")
    public List<Module> subModule();

}
