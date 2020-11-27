package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Comment;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;

import java.util.List;

public interface CommentService {
    ErrorEnum addComment(UserModel userModel, CommentModel commentModel);

    ErrorEnum deleteComment(UserModel userModel, CommentModel commentModel);

    List<Comment> showAllComments();

}
