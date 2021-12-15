package com.flora.chatter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String body;

    @NotBlank
    private String img;

    private Boolean isPersonal = false;

    @CreationTimestamp
    private Timestamp createdAt;

    @Transient
    private Integer numOfLikes;
    @Transient
    private Integer numOfComments;

//    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = AppUser.class)
    @JoinColumn(name = "user_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;
}
