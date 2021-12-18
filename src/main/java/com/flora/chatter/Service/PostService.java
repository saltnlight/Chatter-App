package com.flora.chatter.Service;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Payload.Request.PostReq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostService {
    Post createPost(PostReq postReq, AppUser user);

    List<Post> fetchAllPublicPost();

    List<Post> fetchAllUserPrivatePost(Long id);

    List<Post> fetchAllUserPost(Long id);

    Post updatePost(PostReq postReq, AppUser user);

    boolean deletePost(Long postId);
}
