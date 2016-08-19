package com.leoman.reserve.service.impl;

import com.leoman.common.service.impl.GenericManagerImpl;
import com.leoman.reserve.dao.ReserveTeamDao;
import com.leoman.reserve.entity.ReserveTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/8/19.
 */
@Service
public class ReserveTeamServiceImpl extends GenericManagerImpl<ReserveTeam,ReserveTeamDao>{

    @Autowired
    private ReserveTeamDao dao;

}
