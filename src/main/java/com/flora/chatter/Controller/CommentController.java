package com.flora.chatter.Controller;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.CommentReq;
import com.flora.chatter.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create-comment")
    public ModelAndView createComment(CommentReq commentReq, HttpServletRequest request, @RequestParam Long commentPostId){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        commentReq.setPostID(commentPostId);
        commentService.createComment(commentReq,user);
        ModelAndView modelAndView = new ModelAndView("redirect:/feed");
        return modelAndView;
    }


}
