package com.leoman.stadium.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.stadium.entity.StadiumSub;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/21.
 */
public interface StadiumSubDao extends IBaseJpaRepository<StadiumSub> {

    @Query("SELECT a FROM StadiumSub a WHERE a.stadiumId=?1 AND a.code=?2")
    public StadiumSub findSite(Long stadiumId,String code);
}
