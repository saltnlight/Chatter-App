package com.flora.chatter.Repository;

import com.flora.chatter.Model.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
}
