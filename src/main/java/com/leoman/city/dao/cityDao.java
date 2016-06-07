package com.leoman.city.dao;

import com.leoman.city.entity.City;
import com.leoman.common.dao.IBaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/6/6.
 */
public interface CityDao extends IBaseJpaRepository<City> {
}
