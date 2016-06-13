package com.leoman.insurance.service;

import com.leoman.common.service.GenericManager;
import com.leoman.insurance.entity.Insurance;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface InsuranceService extends GenericManager<Insurance> {

    public List<Insurance> findBySysInsuranceId(Long id);
}
