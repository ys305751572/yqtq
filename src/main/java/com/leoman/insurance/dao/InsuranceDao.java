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
}
