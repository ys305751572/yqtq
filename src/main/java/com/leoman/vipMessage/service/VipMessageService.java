package com.leoman.vipMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.vipMessage.entity.VipMessage;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface VipMessageService extends GenericManager<VipMessage>  {

    public VipMessage findById(Long id);
}
