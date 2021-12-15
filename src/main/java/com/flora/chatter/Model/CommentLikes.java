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
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String body;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "user_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY,  targetEntity = Comment.class)
    @JoinColumn(name = "post_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment comment;
}
