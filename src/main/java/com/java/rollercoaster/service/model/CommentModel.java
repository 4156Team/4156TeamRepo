package com.java.rollercoaster.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentModel {
    private Integer commentId;
    private Integer userId;
    private String userName;
    private Date commentTime;
    private String commentContent;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCommentTime() {
        return new Date(commentTime.getTime());
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = new Date(commentTime.getTime());
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
