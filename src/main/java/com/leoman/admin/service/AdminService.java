package com.leoman.admin.service;


import com.leoman.admin.entity.Admin;
import com.leoman.common.service.ICommonService;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface AdminService extends ICommonService<Admin> {

    public Admin findByUsername(String username);
}
