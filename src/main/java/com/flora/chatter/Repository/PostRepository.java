package com.flora.chatter.Repository;

import com.flora.chatter.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByIsPersonalFalseOrderByCreatedAtDesc();

    @Query("SELECT post FROM Post as post WHERE post.user.id = :userID AND post.isPersonal = true")
    List<Post> findPrivateByUserId(@Param("userID") Long userID);

    List<Post> findAllByUserIdOrderByCreatedAtDesc(Long id);
}
