package com.leoman.girlServiceMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.girlServiceMessage.entity.GirlServiceMessage;


/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface GirlServiceMessageService extends GenericManager<GirlServiceMessage>  {

    public GirlServiceMessage findById(Long id);
}
