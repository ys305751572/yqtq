package com.leoman.information.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.information.entity.Information;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/8 0008 下午 4:32.
 */
public interface InformationDao extends IBaseJpaRepository<Information>,JpaSpecificationExecutor<Information> {

    public Information findById(Long id);
}