package com.flora.chatter.Repository;

import com.flora.chatter.Model.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findByPostIdAndUserId(Long postId, Long userId);

    List<PostLikes> findAllByPostId(Long id);
}
