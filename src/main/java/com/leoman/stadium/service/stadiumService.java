package com.leoman.stadium.service;

import com.leoman.common.service.GenericManager;
import com.leoman.stadium.entity.Stadium;
import com.leoman.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface StadiumService extends GenericManager<Stadium> {

    public List<Stadium> findAll();

    public Stadium findById(Long id);

    public Page<Stadium> findAll(Stadium stadium, Integer currentPage, Integer pageSize) throws Exception;

    public List<User> findByNickName(Long id);

}
