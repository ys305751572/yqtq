package com.leoman.city.entity;

import com.leoman.common.entity.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 区
 * Created by Administrator on 2016/8/11.
 */
@Entity
@Table(name = "t_area")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "area")
    private String area;

    @Column(name = "city_id")
    private Long cityId;

    public Long getAreaId() {
        return areaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
