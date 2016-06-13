package com.leoman.systemInsurance.service;

import com.leoman.common.service.GenericManager;
import com.leoman.common.service.ICommonService;
import com.leoman.systemInsurance.entity.SystemInsurance;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface SystemInsuranceService  extends GenericManager<SystemInsurance> {

    public Page<SystemInsurance> findPage(int pageNum, int pageSize);
}
