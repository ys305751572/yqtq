package com.leoman.user.dao;

import com.leoman.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface UserDao extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    public User findByUserId(Long userId);

}
