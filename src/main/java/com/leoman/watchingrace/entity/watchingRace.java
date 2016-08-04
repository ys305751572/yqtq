package com.leoman.watchingrace.entity;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.common.entity.BaseEntity;
import com.leoman.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/8.
 */
@Entity
@Table(name = "t_watching_race")
public class WatchingRace extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    @Column(name = "avater")
    private String avater;

    @Column(name = "status")
    private Integer status;

    @Column(name = "invitation")
    private Integer invitation;

    @Column(name = "description")
    private String description;

    @Transient
    private String avaterAbsolutePath;

    public String getAvaterAbsolutePath() {
        if(StringUtils.isNotBlank(avater)){
            return ConfigUtil.getString("upload.url") + getAvater();
        }else {
            return "";
        }
    }

    public void setAvaterAbsolutePath(String avaterAbsolutePath) {
        this.avaterAbsolutePath = avaterAbsolutePath;
    }

    public Integer getInvitation() {
        return invitation;
    }

    public void setInvitation(Integer invitation) {
        this.invitation = invitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
