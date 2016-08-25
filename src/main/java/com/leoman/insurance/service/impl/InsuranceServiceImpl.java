package com.leoman.insurance.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.insurance.dao.InsuranceDao;
import com.leoman.insurance.entity.Insurance;
import com.leoman.insurance.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
@Service
public class InsuranceServiceImpl extends GenericManagerImpl<Insurance, InsuranceDao> implements InsuranceService {

    @Autowired
    private InsuranceDao dao;

    @Override
    public List<Insurance> findBySysInsuranceId(Long id) {
        return dao.findBySysInsuranceId(id);
    }

    @Override
    public Insurance findInsurance(Long userId, Long reserveId) {
        return dao.findInsurance(userId,reserveId);
    }
}
