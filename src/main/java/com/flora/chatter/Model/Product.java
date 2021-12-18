package com.flora.chatter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "productId")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Post{
//    @NotBlank(message = "Description field is required")
    private String description;

//    @NotBlank(message = "Price field is required")
    private Double price;

    public Product(Long id, @NotBlank String body, @NotBlank String img, Boolean isPersonal, Boolean isProduct, Timestamp createdAt, Integer numOfLikes, Integer numOfComments, AppUser user, String description, Double price) {
        this.description = description;
        this.price = price;
    }

//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
//    @JoinColumn(name = "order_fk")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Order order;
}
