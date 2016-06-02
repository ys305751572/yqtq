package com.leoman.reserve.dao;

import com.leoman.reserve.entity.Reserve;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systemInsurance.entity.SystemInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface ReserveDao extends JpaRepository<Reserve, Long>,JpaSpecificationExecutor<Reserve> {

    //查球场名称
    @Query("SELECT a FROM Stadium a WHERE a.id=(SELECT b.stadiumId FROM Reserve b WHERE b.id=?1)")
    public List<Stadium> findStadiumName(Long id);

}
