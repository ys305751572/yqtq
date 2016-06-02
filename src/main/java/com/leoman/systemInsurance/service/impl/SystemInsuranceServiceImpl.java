package com.leoman.systemInsurance.service.impl;

import com.leoman.systemInsurance.dao.SystemInsuranceDao;
import com.leoman.systemInsurance.entity.SystemInsurance;
import com.leoman.systemInsurance.service.SystemInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Service
public class SystemInsuranceServiceImpl implements SystemInsuranceService{

    @Autowired
    private SystemInsuranceDao dao;
    @Override
    public List<SystemInsurance> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<SystemInsurance> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<SystemInsurance> find(int pageNum) {
        return null;
    }

    @Override
    public SystemInsurance getById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public SystemInsurance deleteById(Long id) {
        return null;
    }

    @Override
    public SystemInsurance create(SystemInsurance systemInsurance) {
        return null;
    }

    @Override
    public SystemInsurance update(SystemInsurance systemInsurance) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }
}
