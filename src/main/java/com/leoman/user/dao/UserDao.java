package com.leoman.user.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface UserDao extends IBaseJpaRepository<User>{

    public User findByUserId(Long userId);

    //只要不是组队失败
    @Query("SELECT SUM(a.price) FROM Reserve a WHERE a.user.id=?1 AND a.status <> '2'")
    public Double SumPrice(Long id);

}
