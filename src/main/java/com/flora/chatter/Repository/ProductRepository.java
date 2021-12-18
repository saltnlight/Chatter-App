package com.flora.chatter.Repository;

import com.flora.chatter.Model.Post;
import com.flora.chatter.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Post> findAllByIsPersonalFalseOrderByCreatedAtDesc();
//
//    @Query("SELECT post FROM Post as post WHERE post.user.id = :userID AND post.isPersonal = true")
//    List<Post> findPrivateByUserId(@Param("userID") Long userID);
//
//    List<Post> findAllByUserIdOrderByCreatedAtDesc(Long id);
}
