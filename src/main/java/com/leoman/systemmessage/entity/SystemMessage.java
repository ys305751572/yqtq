package com.leoman.systemmessage.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/30.
 */
@Entity
@Table(name = "t_system_message")
public class SystemMessage extends BaseEntity{

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
