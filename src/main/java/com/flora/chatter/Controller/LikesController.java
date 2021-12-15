package com.flora.chatter.Controller;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.CommentLikeReq;
import com.flora.chatter.Payload.Request.PostLikeReq;
import com.flora.chatter.Service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LikesController {
    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/post-reaction")
    public String likeUnlikePost(HttpServletRequest request, PostLikeReq postLikeReq, @RequestParam Long likePostId){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        postLikeReq.setPostID(likePostId);
        likesService.likeUnlikePost(postLikeReq, user);
        return "redirect:/feed";
    }

    @GetMapping("/comment-reaction")
    public String likeUnlikeComment(HttpServletRequest request, CommentLikeReq commentLikeReq){
        return "redirect:/feed";
    }
}
