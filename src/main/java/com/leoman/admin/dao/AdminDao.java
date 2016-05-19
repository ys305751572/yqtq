package com.leoman.admin.dao;


import com.leoman.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/3/8.
 */
public interface AdminDao extends JpaRepository<Admin,Long> {

    @Query("select a from Admin a where a.username = ?1")
    public Admin findByUsername(String username);
}
