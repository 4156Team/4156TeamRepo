package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.CommentMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.CommentService;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CommentControllerTest {
    @Autowired
    CommentService commentService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    CommentController commentController;
    @Autowired
    CommentMapper commentMapper;

    @Test
    public void postAndDeleteCommentTest() {
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentTime(new Date());
        commentModel.setCommentContent("comment controller test");

        UserModel userModel = new UserModel();
        userModel.setUserName("Aria");
        userModel.setUserId(3);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        commentController.postComment(commentModel);
        assertEquals("comment controller test", commentMapper.selectByPrimaryKey(commentModel.getCommentId()).getCommentContent());
        commentController.deleteComment(commentModel);
        assertEquals(null, commentMapper.selectByPrimaryKey(commentModel.getCommentId()));


    }


    @Test
    public void NotLoginCommentTest() {
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentTime(new Date());
        commentModel.setCommentContent("comment controller test");
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, commentController.postComment(commentModel).getData());
        assertEquals(ErrorEnum.USER_NOT_LOGIN, commentController.deleteComment(commentModel).getData());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, commentController.postComment(commentModel).getData());
        assertEquals(ErrorEnum.USER_NOT_LOGIN, commentController.deleteComment(commentModel).getData());
    }

    @Test
    public void userNotExistCommentTest() {
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentTime(new Date());
        commentModel.setCommentContent("comment controller test");

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);

        assertEquals(ErrorEnum.USER_NOT_EXIST, commentController.postComment(commentModel).getData());
        assertEquals(ErrorEnum.USER_NOT_EXIST, commentController.deleteComment(commentModel).getData());
    }

    @Test
    public void showRecordsTest() {
        assertEquals("success", commentController.showComments().getStatus());
    }

}
