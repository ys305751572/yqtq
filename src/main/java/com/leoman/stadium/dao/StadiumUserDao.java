package com.leoman.stadium.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.stadium.entity.StadiumUser;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/6/12.
 */
public interface StadiumUserDao extends IBaseJpaRepository<StadiumUser> {

    @Query("SELECT COUNT(a) FROM Stadium a WHERE a.stadiumUserId = ?1")
    public Integer stadiumNum(Long id);

    @Query("SELECT SUM(a.price) FROM StadiumSub a WHERE a.stadiumId IN (SELECT b.id FROM Stadium b WHERE b.stadiumUserId = ?1)")
    public Double toDaySumPrice(Long id);

//    @Query("SELECT a.price,b.bookTime FROM StadiumSub a JOIN (SELECT stadiumSubId,bookTime,createDate FROM StadiumBooking WHERE stadiumId IN (SELECT id FROM Stadium WHERE stadiumUserId = ?1)) b ON a.id = b.stadiumSubId WHERE b.createDate=?2")

}
