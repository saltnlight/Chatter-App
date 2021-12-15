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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotBlank(message = "Description field is required")
    private String title;

    @NotBlank(message = "Price field is required")
    private Double price;

    public Product(Long id, @NotBlank String body, @NotBlank String img, Boolean isPersonal, Timestamp createdAt, Integer numOfLikes, Integer numOfComments, AppUser user, String title, Double price) {
        super(id, body, img, isPersonal, createdAt, numOfLikes, numOfComments, user);
        this.title = title;
        this.price = price;
    }


    //    @NotEmpty
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
//    @JoinColumn(name = "user_fk")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private AppUser user;
}
