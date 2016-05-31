package com.leoman.stadium.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.reserve.entity.Reserve;
import com.leoman.reserve.entity.ReserveTeam;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

/**
 * 球场表
 * Created by Administrator on 2016/5/30.
 */
@Entity
@Table(name = "t_stadium")
public class Stadium extends BaseEntity {
    //城市ID
    @Column(name = "city_id")
    private Long cityId;
    //球场主ID
    @Column(name = "stadium_user_id")
    private Long stadiumUserId;
    //球场名称
    @Column(name = "name")
    private Long name;
    //球场类型 0:私人球场 1:公共球场
    @Column(name = "type")
    private Integer type;
    //球场信息
    @Column(name = "description")
    private String description;

    //预定总数
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    private Set<ReserveTeam> rtSet;
    @Transient
    private Integer stadiumNum;

    //当前预定数
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    @Where(clause = "status = '1'")
    private Set<ReserveTeam> rSet;
    @Transient
    private Integer availableStadiumNum;

    //场地数
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    private Set<StadiumSub> ssSet;
    @Transient
    private Integer siteNum;

    //状态
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    @Where(clause = "status = '1'")
    private Set<StadiumSub> ssStatus;
    @Transient
    private Integer isStatus;

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

    public Set<ReserveTeam> getrSet() {
        return rSet;
    }

    public void setrSet(Set<ReserveTeam> rSet) {
        this.rSet = rSet;
    }

    public Integer getAvailableStadiumNum() {
        return rSet.size();
    }

    public void setAvailableStadiumNum(Integer availableStadiumNum) {
        this.availableStadiumNum = availableStadiumNum;
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

    public Set<ReserveTeam> getRtSet() {
        return rtSet;
    }

    public void setRtSet(Set<ReserveTeam> rtSet) {
        this.rtSet = rtSet;
    }

    public Integer getStadiumNum() {
        return rtSet.size();
    }

    public void setStadiumNum(Integer stadiumNum) {
        this.stadiumNum = stadiumNum;
    }

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
