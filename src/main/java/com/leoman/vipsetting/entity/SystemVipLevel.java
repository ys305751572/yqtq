package com.leoman.vipsetting.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/29.
 */
@Entity
@Table(name = "t_system_vip_level")
public class SystemVipLevel extends BaseEntity{
    //vip ID
    @Column(name = "system_vip_id")
    private Long systemVipId;
    //等级
    @Column(name = "level")
    private Integer level;
    //需要经验
    @Column(name = "experience")
    private Integer experience;
    //优惠，这里主要是打折值
    @Column(name = "preferente")
    private Double preferente;
    //时间期限（1：一年，2：两年，3：三年）
    @Column(name = "type")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSystemVipId() {
        return systemVipId;
    }

    public void setSystemVipId(Long systemVipId) {
        this.systemVipId = systemVipId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Double getPreferente() {
        return preferente;
    }

    public void setPreferente(Double preferente) {
        this.preferente = preferente;
    }
}
