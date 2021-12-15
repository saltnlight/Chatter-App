package com.flora.chatter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "user_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY,  targetEntity = Post.class)
    @JoinColumn(name = "post_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}
