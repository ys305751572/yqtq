package com.leoman.stadium.service;

import com.leoman.common.service.GenericManager;
import com.leoman.stadium.entity.StadiumSub;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/21.
 */
public interface StadiumSubService extends GenericManager<StadiumSub> {

    public StadiumSub findSite(Long stadiumId,String code);

}
