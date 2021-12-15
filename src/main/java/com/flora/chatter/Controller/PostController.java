package com.flora.chatter.Controller;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Payload.Request.PostReq;
import com.flora.chatter.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService psl) {
        this.postService = psl;
    }

    @RequestMapping("start-conversation")
    public String postPage(Model model) {
        model.addAttribute("createPost", new PostReq());
        model.addAttribute("ePostId", 0L);
        return "post";
    }

    @GetMapping("edit-conversation")
    public String postPage(Model model, @RequestParam Post editPostObj) {
//        log.info("editposst {}",editPostObj.getId());
        model.addAttribute("createPost", new PostReq());
        model.addAttribute("ePostId", editPostObj.getId());
        model.addAttribute("editPost", editPostObj);
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println(editPostObj.getId());
        System.out.println(editPostObj);
        System.out.println("''''''''''''''''''''''''''''''''''''''''''''");
        return "post";
    }

    @PostMapping("/new-post")
    public ModelAndView savePost(PostReq postReq, HttpServletRequest request, @RequestParam Long postId){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
//        log.info("post.img {}", postReq.getImg());
        System.out.println("''''''''''''''''''''''''''''");
        System.out.println(user.getId());
        System.out.println(postId);
        System.out.println("''''''''''''''''''''''''''''");
        if(postId > 0) {
            postReq.setId(postId);
            postService.updatePost(postReq, user);
        }
        else postService.createPost(postReq, user);
        return new ModelAndView("redirect:/feed");
    }

    @GetMapping("/delete-post")
    public ModelAndView deletePost(@RequestParam Long delPostId){
        boolean reply = postService.deletePost(delPostId);
        log.info("Got Here");
        //if (reply){} //somehow display a success modal in home page
        //else {//somehow display a failure modal in home page}
        return new ModelAndView("redirect:/feed");
    }

//    @PostMapping("/update-post")
//    private ModelAndView updatePost(Post post, HttpServletRequest request, @RequestParam Long postid){
//        System.out.println();
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        System.out.println("updatepost id "+postid);
//        System.out.println("updatepost "+postReq.getBody());
//        postService.updatePost(postReq, user, postid);
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");

//        return modelAndView;
//    }
}
