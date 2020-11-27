package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Comment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.CommentService;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.servlet.http.HttpServletRequest;


@Controller("comment")
@RequestMapping("/comment")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class CommentController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private CommentService commentService;


    /**
     * Post comment endpoint.
     * @param commentModel input comment data model
     * @return common return type
     */
    @PostMapping("/postComment")
    @ResponseBody
    public CommonReturnType postComment(@RequestBody CommentModel commentModel) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        return CommonReturnType.autoCreate(commentService.addComment(userModel, commentModel));
    }

    /**
     * Delete a comment record.
     * @param commentModel input comment data model
     * @return common return type
     */
    @PostMapping("/deleteComment")
    @ResponseBody
    public CommonReturnType deleteComment(@RequestBody CommentModel commentModel) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        return CommonReturnType.autoCreate(commentService.deleteComment(userModel, commentModel));
    }

    /**
     * Show all the comments.
     * @return all the comments records
     */
    @RequestMapping(value = "/showComments", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType showComments() {
        List<Comment> commentList = commentService.showAllComments();
        return CommonReturnType.create(commentList);
    }




}
