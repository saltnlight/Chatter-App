//package com.flora.chatter.Model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.aspectj.weaver.ast.Or;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
//    @JoinColumn(name = "user_fk", referencedColumnName = "id")
//    private AppUser user;
//
//    @OneToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
//    @JoinColumn(name = "order_fk", referencedColumnName = "id")
//    private Order order;
//}
