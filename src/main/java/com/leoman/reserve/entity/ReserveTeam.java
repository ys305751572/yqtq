package com.leoman.reserve.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.stadium.entity.Stadium;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

/**
 * 球队球场预订表
 * Created by Administrator on 2016/5/31.
 */
@Entity
@Table(name = "t_reserve_team")
public class ReserveTeam extends BaseEntity {
    //预定用户ID
    @Column(name = "user_id")
    private Integer userId;
    //预定球场ID
    @Column(name = "stadium_id")
    private Long stadiumId;
    //预定场次ID 私有
    @Column(name = "site_id")
    private Integer siteId;
    //城市ID
    @Column(name = "city_id")
    private Integer cityId;
    //价格
    @Column(name = "price")
    private Double price;
    //保险ID
    @Column(name = "insurance_id")
    private Integer insuranceId;
    //状态0:使用 1:未使用
    @Column(name = "status")
    private Integer status;

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }
}
