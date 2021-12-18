package com.flora.chatter.Controller;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Payload.Request.PostReq;
import com.flora.chatter.Service.PostService;
import com.flora.chatter.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@Slf4j
public class PostController {
    private final PostService postService;
    private final ProductService productService;

    @Autowired
    public PostController(PostService psl, ProductService productService) {
        this.postService = psl;
        this.productService = productService;
    }

    @RequestMapping("start-conversation")
    public String postPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        model.addAttribute("createPost", new PostReq());
        model.addAttribute("ePostId", 0L);
        model.addAttribute("user", user);
        return "post";
    }

    @GetMapping("edit-conversation")
    public String postPage(Model model, @RequestParam Post editPostObj, HttpServletRequest request) {
//        log.info("editposst {}",editPostObj.getId());
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        model.addAttribute("createPost", new PostReq());
        model.addAttribute("ePostId", editPostObj.getId());
        model.addAttribute("editPost", editPostObj);
        model.addAttribute("user", user);
        return "post";
    }

    @PostMapping("/new-post")
    public ModelAndView savePost(PostReq postReq, HttpServletRequest request, @RequestParam Long postId) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");

        Part part = request.getPart("file");

        String imageName = part.getSubmittedFileName();

        postReq.setImg(imageName);

        String path = "/Users/mac/IdeaProjects/Chatter/src/main/resources/static/image"+ File.separator+postReq.getImg();

        InputStream in = part.getInputStream();
        boolean success = uploadFile(in,path);

        if (postId > 0){
            postReq.setId(postId);
            if (postReq.getIsProduct()) productService.updateProduct(postReq, user);
            else postService.updatePost(postReq, user);
        }else{
            if (postReq.getIsProduct()) productService.createProduct(postReq, user);
            else postService.createPost(postReq, user);
        }
        return new ModelAndView("redirect:/feed");
    }

    @GetMapping("/privates")
    public ModelAndView privatePostsPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("private-posts");
        List<Post> privatePosts = postService.fetchAllUserPrivatePost(user.getId());
        modelAndView.addObject("privatePosts", privatePosts);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/delete-post")
    public ModelAndView deletePost(@RequestParam Long delPostId){
        boolean reply = postService.deletePost(delPostId);
        log.info("Got Here");
        //if (reply){} //somehow display a success modal in home page
        //else {//somehow display a failure modal in home page}
        return new ModelAndView("redirect:/feed");
    }


    public boolean uploadFile(InputStream in, String path){
        boolean test = false;
        try{
            byte[] byt = new byte[in.available()];
            in.read(byt);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return test;
    }


}
