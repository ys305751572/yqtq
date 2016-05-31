package com.leoman.reserve.dao;

import com.leoman.reserve.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface ReserveDao extends JpaRepository<Reserve, Long>,JpaSpecificationExecutor<Reserve> {

}
