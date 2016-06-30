package com.leoman.userfeedback.entity;

import com.leoman.common.entity.BaseEntity;
import com.leoman.user.entity.User;

import javax.persistence.*;

/**
 * 用户反馈表
 * Created by Administrator on 2016/6/30.
 */
@Entity
@Table(name = "t_user_feedback")
public class UserFeedback extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
