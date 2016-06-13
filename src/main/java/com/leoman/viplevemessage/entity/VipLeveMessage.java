package com.leoman.viplevemessage.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/13.
 */
@Entity
@Table(name = "t_vip_leve_message")
public class VipLeveMessage extends BaseEntity{

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
