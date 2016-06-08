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
    private Long cityId;
    //城市名
    @Column(name = "City")
    private String city;
    //省id
    @Column(name = "province_id")
    private Long provinceId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
