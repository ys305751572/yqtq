package com.leoman.stadium.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.stadium.entity.Stadium;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface StadiumDao extends IBaseJpaRepository<Stadium> {

    public Stadium findById(Long id);
}
