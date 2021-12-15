package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Exception.PostException;
import com.flora.chatter.Exception.UserException;
import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Payload.Request.PostReq;
import com.flora.chatter.Repository.CommentRepository;
import com.flora.chatter.Repository.PostLikesRepository;
import com.flora.chatter.Repository.PostRepository;
import com.flora.chatter.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository pr, PostLikesRepository postLikesRepository, CommentRepository commentRepository) {
        this.postRepository = pr;
        this.postLikesRepository = postLikesRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    @Transactional
    public Post createPost(PostReq postReq, AppUser user) {
        System.out.println(user);
        Post post = new Post();
        post.setUser(user);
        post.setBody(postReq.getBody());
        post.setImg(postReq.getImg());
        post.setIsPersonal(postReq.getIsPersonal());
        Post savedPost;
        try{
            savedPost = postRepository.save(post);
        }catch (UserException e){
            throw new UserException("Can not create this post");
        }
        return savedPost;
    }

    @Override
    public List<Post> fetchAllPublicPost() {
        List<Post> posts = postRepository.findAllByIsPersonalFalseOrderByCreatedAtDesc();
        for (Post post:posts) {
            post.setNumOfLikes(postLikesRepository.findAllByPostId(post.getId()).size());
            post.setNumOfComments(commentRepository.findCommentsByPostId(post.getId()).size());
        }
        return posts;
    }

    @Override
    public List<Post> fetchAllUserPrivatePost(Long id) {
        List<Post> privatePosts = postRepository.findAllByIdAndIsPersonalTrueOrderByCreatedAtDesc(id);
        return privatePosts;
    }

    @Override
    @Transactional
    public Post updatePost(PostReq postReq, AppUser user) {
        Optional<Post> post = postRepository.findById(postReq.getId());
        Post updatedPost = new Post();
        try{
            if (post.isPresent()){
                updatedPost = post.get();
                updatedPost.setBody(postReq.getBody());
                updatedPost.setImg(postReq.getImg());
                updatedPost.setIsPersonal(postReq.getIsPersonal());
                return updatedPost;
            }
        }catch(PostException e){
            log.error("I don't know how this would even be possible");
            throw new PostException("Can not update this post");
        }
        return updatedPost;
    }

    @Override
    @Transactional
    public boolean deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }
}
