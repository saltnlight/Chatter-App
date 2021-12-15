package com.flora.chatter.Repository;

import com.flora.chatter.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostIdOrderByCreatedAtDesc(Long id);

    List<Comment> findCommentsByPostId(Long id);
}
