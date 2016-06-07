package com.leoman.city.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/6.
 */
@Entity
@Table(name = "t_city")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //城市id
    @Column(name = "city_id")
    private Integer cityId;
    //城市名
    @Column(name = "City")
    private String city;
    //省id
    @Column(name = "province_id")
    private Integer provinceId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
