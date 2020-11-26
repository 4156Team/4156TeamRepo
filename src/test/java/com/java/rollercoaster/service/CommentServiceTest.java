package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.CommentMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Comment;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentMapper commentMapper;

    @Test
    public void addCommentTest() {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserId(1);
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentContent("test comment");
        commentModel.setCommentTime(new Date());
        commentService.addComment(userModel, commentModel);
        Comment comment = commentMapper.selectByPrimaryKey(commentModel.getCommentId());
        assertEquals("test comment", comment.getCommentContent());
        commentMapper.deleteByPrimaryKey(commentModel.getCommentId());

    }

    @Test
    public void deleteCommentTest() {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserId(1);
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentContent("test comment");
        commentModel.setCommentTime(new Date());
        commentService.addComment(userModel, commentModel);
        Comment comment = commentMapper.selectByPrimaryKey(commentModel.getCommentId());
        assertEquals("test comment", comment.getCommentContent());
        commentService.deleteComment(userModel, commentModel);
        Comment comment1 = commentMapper.selectByPrimaryKey(commentModel.getCommentId());
        assertEquals(null, comment1);
    }

    @Test
    public void invalidDeletionTest() {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserId(1);
        CommentModel commentModel = new CommentModel();
        commentModel.setCommentContent("test comment");
        commentModel.setCommentTime(new Date());
        commentService.addComment(userModel, commentModel);

        UserModel userModel1 = new UserModel();
        userModel1.setUserId(2);
        userModel.setUserName("James");
        assertEquals(ErrorEnum.UNAUTHORIZED_DELETION, commentService.deleteComment(userModel1, commentModel));
    }

    @Test
    public void notLoginTest() {
        UserModel userModel = null;
        CommentModel commentModel = new CommentModel();
        assertEquals(ErrorEnum.USER_NOT_LOGIN, commentService.addComment(userModel, commentModel));

    }

    @Test
    public void inValidCommentTest() {
        UserModel userModel = new UserModel();
        CommentModel commentModel = null;
        assertEquals(ErrorEnum.INVALID_COMMENT, commentService.addComment(userModel, commentModel));

    }
}
