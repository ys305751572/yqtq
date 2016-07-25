package com.leoman.stadium.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.stadium.entity.StadiumUserWithdraw;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public interface StadiumUserWithdrawDao extends IBaseJpaRepository<StadiumUserWithdraw> {

    @Query("SELECT a.status FROM StadiumUserWithdraw a WHERE a.stadiumUser.id =?1")
    public List<Integer> findStatus(Long id);

}
