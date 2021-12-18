//package com.flora.chatter.Controller;
//
//import com.flora.chatter.Model.AppUser;
//import com.flora.chatter.Payload.Request.OrderReq;
//import com.flora.chatter.Service.OrderService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//@Slf4j
//public class OrderController {
//    private OrderService orderService;
//
//    @Autowired
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/add-to-cart")
//    public String createOrder(HttpServletRequest request, @RequestParam Long productID){
//        HttpSession session = request.getSession();
//        AppUser user = (AppUser) session.getAttribute("user");
//        log.info("product obj from FE {}", productID);
////        orderService.createOrder(productID, user);
//        return "redirect:/feed";
//    }
//}
