package com.flora.chatter.Controller;

import com.flora.chatter.Exception.UserException;
import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.*;
import com.flora.chatter.Service.AppUserService;
import com.flora.chatter.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;
    private final PostService postService;

    @Autowired
    public AppUserController(AppUserService appUserService, PostService postService) {
        this.appUserService = appUserService;
        this.postService = postService;
    }

    @GetMapping("/chatter")
    public ModelAndView landingPage(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerUser", new RegisterReq());
        modelAndView.addObject("loginUser", new LoginReq());
        return modelAndView;
    }

    @GetMapping("/feed")
    public ModelAndView feedPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("chatter");
        modelAndView.addObject("user", user);
        modelAndView.addObject("posts", postService.fetchAllPublicPost());
        modelAndView.addObject("commentReq", new CommentReq());
        modelAndView.addObject("PLReq", new PostLikeReq());
        modelAndView.addObject("CLReq", new CommentLikeReq());
        return modelAndView;
    }

    @PostMapping("/createUser")
    public ModelAndView createUser(@Valid RegisterReq registerUserReq, HttpServletRequest request, BindingResult bindingResult){
        HttpSession httpSession = request.getSession();

        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            log.info("allErrors::{}", allErrors.get(0));
            log.info("allErrors {} ",allErrors);
            httpSession.setAttribute("signupMessage", "Error Signing Up");
            return new ModelAndView("redirect:/chatter");
        }

        httpSession.removeAttribute("loginMessage");
        try{
            appUserService.createUser(registerUserReq);
        }catch (UserException e){
            System.out.println("Can not sign up user");
            return  new ModelAndView("error");
        }
        return new ModelAndView("redirect:/chatter");
    }

    @PostMapping("/loginUser")
    public ModelAndView loginUser(LoginReq loginUserReq, HttpServletRequest request){

        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("signupMessage");
        new AppUser();
        AppUser user;
        try{
            user = appUserService.loginUser(loginUserReq);
        }catch (UserException e){
            ModelAndView modelAndView = new ModelAndView("redirect:/chatter");
            httpSession.setAttribute("loginMessage", "Wrong credentials\nInvalid username or password");
            System.out.println("Can not login user");
            return modelAndView;
        }
        httpSession.setAttribute("user",user);
        System.out.println("Sign up successful");
        return new ModelAndView("redirect:/feed");
    }
}
