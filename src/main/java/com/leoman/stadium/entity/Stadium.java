package com.leoman.stadium.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 球场表
 * Created by Administrator on 2016/5/30.
 */
@Entity
@Table(name = "t_stadium")
public class Stadium extends BaseEntity {

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "stadium_user_id")
    private Long stadiumUserId;

    @Column(name = "name")
    private Long name;

    @Column(name = "type")
    private Integer type;

    @Column(name = "description")
    private String description;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStadiumUserId() {
        return stadiumUserId;
    }

    public void setStadiumUserId(Long stadiumUserId) {
        this.stadiumUserId = stadiumUserId;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
