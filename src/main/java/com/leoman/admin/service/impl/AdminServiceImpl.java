package com.leoman.admin.service.impl;

import com.leoman.admin.dao.AdminDao;
import com.leoman.admin.entity.Admin;
import com.leoman.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Page<Admin> find(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public Page<Admin> find(int pageNum) {
        return null;
    }

    @Override
    public Admin getById(Long id) {
        return null;
    }

    @Override
    public Admin deleteById(Long id) {
        return null;
    }

    @Override
    public Admin create(Admin admin) {
        return null;
    }

    @Override
    public Admin update(Admin admin) {
        return null;
    }

    @Override
    public void deleteAll(Long[] ids) {

    }

    @Override
    public Admin findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
