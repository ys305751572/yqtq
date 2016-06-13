package com.leoman.vipLevelMessage.service;

import com.leoman.common.service.GenericManager;
import com.leoman.vipLevelMessage.entity.VipLevelMessage;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public interface VipLevelMessageService extends GenericManager<VipLevelMessage>  {

    public VipLevelMessage findById(Long id);
}
