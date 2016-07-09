package com.leoman.reserve.service;

import com.leoman.common.service.GenericManager;
import com.leoman.common.service.ICommonService;
import com.leoman.reserve.entity.Reserve;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systemInsurance.entity.SystemInsurance;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by Administrator on 2016/5/24.
 */
public interface ReserveService extends GenericManager<Reserve> {

    public Page<Reserve> findPage(String details,Reserve reserve, int pagenum, int pagesize);

    public Integer findNum(Long reserveId);

    public Long findStadiumBookingId(Long stadiumId,Long userId,Long createDate);

}
