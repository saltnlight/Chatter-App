package com.flora.chatter.Repository;

import com.flora.chatter.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByIsPersonalFalseOrderByCreatedAtDesc();

    List<Post> findAllByIdAndIsPersonalTrueOrderByCreatedAtDesc(Long id);

    //    @Query("SELECT pl FROM Post_Likes as pl WHERE pl.post.id = :postID AND pl.user.id = :userID")
//    Optional<Post_Likes> findByPostIdAndUserId(@Param("postID") Long postID, @Param("userID") Long userID);

//    Optional<Post_Likes> findByPostIdAndUserId(Long postID, Long userID);

}
