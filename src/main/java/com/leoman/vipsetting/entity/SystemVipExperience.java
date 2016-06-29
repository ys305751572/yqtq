package com.leoman.vipsetting.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/29.
 */
@Entity
@Table(name = "t_system_vip_experience")
public class SystemVipExperience extends BaseEntity{
    //vip ID
    @Column(name = "system_vip_id")
    private Long systemVipId;
    //经验
    @Column(name = "experience")
    private Integer experience;
    //获得经验动作(1：散客组队成功 2：场地预订 3：约看宝贝)
    @Column(name = "action")
    private Integer action;
    //编码
    @Column(name = "code")
    private String code;

    public Long getSystemVipId() {
        return systemVipId;
    }

    public void setSystemVipId(Long systemVipId) {
        this.systemVipId = systemVipId;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
