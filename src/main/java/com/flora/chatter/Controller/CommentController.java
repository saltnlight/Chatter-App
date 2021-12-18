package com.flora.chatter.Controller;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.CommentReq;
import com.flora.chatter.Payload.Request.LoginReq;
import com.flora.chatter.Payload.Request.RegisterReq;
import com.flora.chatter.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comment-page", method = RequestMethod.GET)
    public ModelAndView commentPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("redirect:/comment");
        modelAndView.addObject("CommentReq", new CommentReq());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/create-comment")
    public ModelAndView createComment(CommentReq commentReq, HttpServletRequest request, @RequestParam Long commentPostId){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        commentReq.setPostID(commentPostId);
        var comment  = commentService.createComment(commentReq,user);
        ModelAndView modelAndView = new ModelAndView("comment");
        modelAndView.addObject("comments", commentService.fetchAllCommentPerPost(comment.getPost().getId()));
        modelAndView.addObject("post", comment.getPost());
        modelAndView.addObject("user", user);
        return modelAndView;
    }


}
