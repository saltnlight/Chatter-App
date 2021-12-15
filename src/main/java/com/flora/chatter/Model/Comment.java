package com.flora.chatter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable=false)
    private String body;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "user_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY,  targetEntity = Post.class)
    @JoinColumn(name = "post_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Transient
    private Integer numOfLikes;
}
