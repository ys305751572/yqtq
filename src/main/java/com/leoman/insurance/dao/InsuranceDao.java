package com.leoman.insurance.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.insurance.entity.Insurance;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface InsuranceDao extends IBaseJpaRepository<Insurance>,JpaSpecificationExecutor<Insurance> {

    @Query("select a from Insurance a where a.systemInsurance.id = ?1")
    public List<Insurance> findBySysInsuranceId(Long id);

    //根据预约表的预约ID和用户ID查询 对应的保险信息
    @Query("SELECT a FROM Insurance a WHERE a.user.id=?1 AND a.reserve.id=?2")
    public Insurance findInsurance(Long userId,Long reserveId);
}
