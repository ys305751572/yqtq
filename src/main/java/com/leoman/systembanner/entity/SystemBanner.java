package com.leoman.systembanner.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/29.
 */
@Entity
@Table(name = "t_system_banner")
public class SystemBanner extends BaseEntity{

    @Column(name = "avater")
    private String avater;

    @Column(name = "to_id")
    private Long toId;

    @Column(name = "type")
    private Integer type;

    public String getAvater() {
        if(StringUtils.isNotBlank(avater)){
            return ConfigUtil.getString("upload.url") + this.avater;
        }else {
            return "";
        }
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
