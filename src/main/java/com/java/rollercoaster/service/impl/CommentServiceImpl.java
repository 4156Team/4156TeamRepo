package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.CommentMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Comment;
import com.java.rollercoaster.pojo.CommentExample;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.service.CommentService;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;



    @Override
    public ErrorEnum addComment(UserModel userModel, CommentModel commentModel)  {
        if (userModel == null) {
            return ErrorEnum.USER_NOT_LOGIN;
        }
        if (commentModel == null) {
            return  ErrorEnum.INVALID_COMMENT;
        }
        commentModel.setUserName(userModel.getUserName());
        commentModel.setUserId(userModel.getUserId());
        Comment comment = convertFromModel(commentModel);

        commentMapper.insertSelective(comment);

        commentModel.setCommentId(comment.getCommentId());
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum deleteComment(UserModel userModel, CommentModel commentModel) {
        if (!userModel.getUserId().equals(commentModel.getUserId())) {
            return ErrorEnum.UNAUTHORIZED_DELETION;
        }
        commentMapper.deleteByPrimaryKey(commentModel.getCommentId());
        return ErrorEnum.OK;
    }

    @Override
    public List<Comment> showAllComments() {

        List<Comment> commentList = commentMapper.selectByExampleWithBLOBs(new CommentExample());
        return commentList;
    }

    private Comment convertFromModel(CommentModel commentModel) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentModel,comment);
        return  comment;
    }
}
