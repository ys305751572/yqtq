package com.leoman.girl.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.User;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/6/8.
 */
//宝贝评论表
@Entity
@Table(name = "t_girl_comment")
public class GirlComment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "girl_id")
    private Girl girl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "star")
    private String star;
    @Column(name = "content")
    private String content;

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
