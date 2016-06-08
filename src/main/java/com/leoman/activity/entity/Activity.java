package com.leoman.activity.entity;

import com.leoman.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Entity
@Table(name = "t_activity")
public class Activity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "avater")
    private String aveter;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAveter() {
        return aveter;
    }

    public void setAveter(String aveter) {
        this.aveter = aveter;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
