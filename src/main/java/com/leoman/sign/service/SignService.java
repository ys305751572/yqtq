package com.leoman.sign.service;

import com.leoman.common.service.GenericManager;
import com.leoman.sign.entity.Sign;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface SignService extends GenericManager<Sign>{

    public Page<Sign> findPage(Integer pagenum,Integer pagesize);

}
