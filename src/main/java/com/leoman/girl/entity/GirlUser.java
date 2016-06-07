package com.leoman.girl.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/6.
 */
@Entity
@Table(name = "t_girl_user")
public class GirlUser extends BaseEntity{

    //宝贝ID
    @Column(name = "girl_id")
    private Long girlId;
    //用户ID
    @Column(name = "user_id")
    private Long userId;
    //球场ID
    @Column(name ="stadium_id")
    private Long stadiumId;
    //预约时间
    @Column(name = "start_date")
    private Long startDate;
    //赛事ID
    @Column(name = "race_id")
    private Long raceId;
    //价格
    @Column(name = "price")
    private Double price;
    //小费
    @Column(name = "tip")
    private Double tip;
    //时长
    @Column(name = "duration")
    private Integer duration;



}
