package com.leoman.systemvipcredibility.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/30.
 */
@Entity
@Table(name = "t_system_vip_credibility")
public class SystemVipCredibility extends BaseEntity{

    @Column(name = "system_vip_id")
    private Long systemVipId;

    @Column(name = "credibility")
    private Integer credibility;

    @Column(name = "action")
    private Integer action;

    @Column(name = "code")
    private String code;

    public Long getSystemVipId() {
        return systemVipId;
    }

    public void setSystemVipId(Long systemVipId) {
        this.systemVipId = systemVipId;
    }

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
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
