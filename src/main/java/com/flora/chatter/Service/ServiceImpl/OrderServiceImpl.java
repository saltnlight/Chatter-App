//package com.flora.chatter.Service.ServiceImpl;
//
//import com.flora.chatter.Model.AppUser;
//import com.flora.chatter.Model.Cart;
//import com.flora.chatter.Model.Order;
//import com.flora.chatter.Model.Product;
//import com.flora.chatter.Payload.Request.OrderReq;
//import com.flora.chatter.Repository.CartRepository;
//import com.flora.chatter.Repository.OrderRepository;
//import com.flora.chatter.Repository.ProductRepository;
//import com.flora.chatter.Service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//    private OrderRepository orderRepository;
//    private CartRepository cartRepository;
//    private ProductRepository productRepository;
//
//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository) {
//        this.orderRepository = orderRepository;
//        this.cartRepository = cartRepository;
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public Order createOrder(Long productID, AppUser user) {
//        Optional<Product> optionalProduct = productRepository.findById(productID);
//        Optional<Cart> optionalCart = cartRepository.findByUserId(user.getId());
//        if (optionalCart.isPresent()){
//            Order order = optionalCart.get().getOrder();
//            optionalProduct.get().setOrder(order);
////            order.setProductSet((Set<Product>) optionalProduct.get());
//            return orderRepository.save(order);
//        }
//        else{
//            Order order = new Order();
////            order.setProductSet((Set<Product>) optionalProduct.get());
//            optionalProduct.get().setOrder(order);
//            Cart cart = new Cart();
//            cart.setUser(user);
//            cart.setOrder(order);
//            cartRepository.save(cart);
//            return orderRepository.save(order);
//        }
//    }
//
//    @Override
//    public Boolean deleteOrder(OrderReq orderReq, AppUser user) {
//        return null;
//    }
//}
