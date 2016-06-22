package com.leoman.reserve.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.reserve.entity.Reserve;
import com.leoman.stadium.entity.Stadium;
import com.leoman.systemInsurance.entity.SystemInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface ReserveDao extends IBaseJpaRepository<Reserve> {

    @Query("SELECT COUNT(a) FROM UserReserveJoin a WHERE a.reserveId = ?1")
    public Integer findNum(Long reserveId);

    @Query("SELECT a.id FROM StadiumBooking a WHERE a.stadium.id=?1 AND a.user.id=?2 AND a.startDate=?3")
    public Long findStadiumBookingId(Long stadiumId,Long userId,Long createDate);


}
