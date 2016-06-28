package com.leoman.girl.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.girl.entity.Girl;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public interface GirlDao extends IBaseJpaRepository<Girl> {

    @Query("SELECT COUNT(a) FROM GirlUser a WHERE a.girl.id=?1")
    public Integer findSize(Long id);

    @Query("SELECT a.girl.id FROM GirlUser a WHERE a.startDate >=?1 AND a.startDate <=?2")
    public List<Long> appointmentId(Long startDate,Long endDate);

}
