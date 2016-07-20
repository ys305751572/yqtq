package com.leoman.stadium.entity;

import com.leoman.city.entity.City;
import com.leoman.city.entity.Province;
import com.leoman.common.entity.BaseEntity;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.entity.ReserveTeam;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * 球场表
 * Created by Administrator on 2016/5/30.
 */
@Entity
@Table(name = "t_stadium")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stadium extends BaseEntity {
    //城市ID
    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;
    //省id
    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "province_id")
    private Province province;
    //区id
    @Column(name = "area_id")
    private Integer areaId;
    //球场主ID
    @Column(name = "stadium_user_id")
    private Long stadiumUserId;
    //球场名称
    @Column(name = "name")
    private String name;
    //球场类型 0:私人球场 1:公共球场
    @Column(name = "type")
    private Integer type;
    //球场信息
    @Column(name = "description")
    private String description;
    //场地类型 0:室内 1:室外
    @Column(name = "site_type")
    private Integer siteType;
    //草皮类型
    @Column(name = "sod_type")
    private String sodType;
    //灯光样式
    @Column(name = "light")
    private String light;
    //停车场 0:无 1:免费 2:收费
    @Column(name = "park")
    private Integer park;
    //赠送
    @Column(name = "giving")
    private String giving;
    //球场地址
    @Column(name = "address")
    private String address;
    //经读
    @Column(name = "longitude")
    private Double longitude;
    //纬度
    @Column(name = "latitude")
    private Double latitude;
    //封面
    @Column(name = "avater")
    private String avater;

    //预定总数
    @Transient
    private Integer stadiumNum;

    //当前预定没使用的数
    @Transient
    private Integer availableStadiumNum;

    //场地数
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    private Set<StadiumSub> ssSet;
    @Transient
    private Integer siteNum;

    //状态 场地未使用的
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    @Where(clause = "status = '1'")
    private Set<StadiumSub> ssStatus;
    @Transient
    private Integer isStatus;

    //累计金额
    @Transient
    private Integer accumulatedAmount;

    public Integer getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(Integer accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public String getSodType() {
        return sodType;
    }

    public void setSodType(String sodType) {
        this.sodType = sodType;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public Integer getPark() {
        return park;
    }

    public void setPark(Integer park) {
        this.park = park;
    }

    public String getGiving() {
        return giving;
    }

    public void setGiving(String giving) {
        this.giving = giving;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Set<StadiumSub> getSsStatus() {
        return ssStatus;
    }

    public void setSsStatus(Set<StadiumSub> ssStatus) {
        this.ssStatus = ssStatus;
    }

    public Integer getIsStatus() {
        return ssStatus.size();
    }

    public void setIsStatus(Integer isStatus) {
        this.isStatus = isStatus;
    }

    public Set<StadiumSub> getSsSet() {
        return ssSet;
    }

    public void setSsSet(Set<StadiumSub> ssSet) {
        this.ssSet = ssSet;
    }

    public Integer getSiteNum() {
        return ssSet.size();
    }

    public void setSiteNum(Integer siteNum) {
        this.siteNum = siteNum;
    }

    public Long getStadiumUserId() {
        return stadiumUserId;
    }

    public void setStadiumUserId(Long stadiumUserId) {
        this.stadiumUserId = stadiumUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Integer getStadiumNum() {
        return stadiumNum;
    }

    public void setStadiumNum(Integer stadiumNum) {
        this.stadiumNum = stadiumNum;
    }

    public Integer getAvailableStadiumNum() {
        return availableStadiumNum;
    }

    public void setAvailableStadiumNum(Integer availableStadiumNum) {
        this.availableStadiumNum = availableStadiumNum;
    }
}
