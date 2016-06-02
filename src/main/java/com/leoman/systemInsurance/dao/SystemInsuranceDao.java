package com.leoman.systemInsurance.dao;

import com.leoman.systemInsurance.entity.SystemInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface SystemInsuranceDao extends JpaRepository<SystemInsurance, Long>,JpaSpecificationExecutor<SystemInsurance> {
}
