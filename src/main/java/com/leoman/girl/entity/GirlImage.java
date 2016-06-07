package com.leoman.girl.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/7.
 */
@Entity
@Table(name = "t_girl_image")
public class GirlImage extends BaseEntity{

    //宝贝ID
    @Column(name = "girl_id")
    private Long girlId;
    //图片类型 0:封面 1:相册
    @Column(name = "type")
    private Integer type;
    //图片url
    @Column(name = "url")
    private String url;

    public Long getGirlId() {
        return girlId;
    }

    public void setGirlId(Long girlId) {
        this.girlId = girlId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
