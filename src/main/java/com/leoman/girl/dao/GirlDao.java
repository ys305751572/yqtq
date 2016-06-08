package com.leoman.girl.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.girl.entity.Girl;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface GirlDao extends IBaseJpaRepository<Girl> {

    @Query("SELECT COUNT(a) FROM GirlUser a WHERE a.girl.id=?1")
    public Integer findSize(Long id);

}
