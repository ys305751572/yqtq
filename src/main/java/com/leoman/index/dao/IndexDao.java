package com.leoman.index.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.user.entity.User;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface IndexDao extends IBaseJpaRepository<User>{

    @Query("SELECT COUNT(a) FROM User a WHERE a.createDate >= ?1")
    public Integer newUserNum(Long date);

    @Query("SELECT COUNT(a) FROM Admin a WHERE a.createDate >= ?1")
    public Integer newAdminNum(Long date);

    @Query("SELECT COUNT(a) FROM Reserve a WHERE a.createDate >= ?1")
    public Integer newReserveNum(Long date);

    @Query("SELECT COUNT(a) FROM StadiumBooking a WHERE a.createDate >= ?1")
    public Integer newStadiumBookingNum(Long date);

    @Query("SELECT COUNT(a) FROM Team a WHERE a.createDate >= ?1")
    public Integer newTeamNum(Long date);

    @Query("SELECT COUNT(a) FROM GirlUser a WHERE a.createDate >= ?1")
    public Integer newGirlUserNum(Long date);

    @Query("SELECT COUNT(a) FROM Post a WHERE a.createDate >= ?1")
    public Integer newPostNum(Long date);

    @Query("SELECT COUNT(a) FROM StadiumUserWithdraw a WHERE a.createDate >= ?1")
    public Integer newWithdrawNum(Long date);

}
