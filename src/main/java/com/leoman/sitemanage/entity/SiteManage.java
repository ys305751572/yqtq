package com.leoman.sitemanage.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/8/15.
 */
@Entity
@Table(name = "t_site_manage")
public class SiteManage extends BaseEntity{

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
