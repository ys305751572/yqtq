package com.leoman.reserve.service;

import com.leoman.common.service.ICommonService;
import com.leoman.reserve.entity.Reserve;
import org.springframework.data.domain.Page;


/**
 * Created by Administrator on 2016/5/24.
 */
public interface ReserveService extends ICommonService<Reserve> {

    public Page<Reserve> findPage(Reserve reserve, int pagenum, int pagesize);
}
