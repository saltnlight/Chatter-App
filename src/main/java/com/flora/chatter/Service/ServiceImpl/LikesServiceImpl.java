package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.PostLikes;
import com.flora.chatter.Payload.Request.PostLikeReq;
import com.flora.chatter.Repository.CommentLikesRepository;
import com.flora.chatter.Repository.PostLikesRepository;
import com.flora.chatter.Repository.PostRepository;
import com.flora.chatter.Service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {
    private final PostLikesRepository postLikesRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikesServiceImpl(PostLikesRepository postLikesRepository, CommentLikesRepository commentLikesRepository, PostRepository postRepository) {
        this.postLikesRepository = postLikesRepository;
        this.commentLikesRepository = commentLikesRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public int likeUnlikePost(PostLikeReq postLikeReq, AppUser user) {
        Optional<PostLikes> pl = postLikesRepository.findByPostIdAndUserId(postLikeReq.getPostID(), user.getId());
        if (pl.isPresent()){
            postLikesRepository.deleteById(pl.get().getId());
            return 0;
        }else{
            PostLikes postLike = new PostLikes();
            postLike.setUser(user);
            postLike.setPost(postRepository.findById(postLikeReq.getPostID()).get());
            postLikesRepository.save(postLike);
            return 1;
        }
    }

    @Override
    public int fetchAllPostLikes(Long postId) {
        return 0;
    }
}
