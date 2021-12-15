package com.flora.chatter.Service;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Model.PostLikes;
import com.flora.chatter.Payload.Request.PostLikeReq;
import com.flora.chatter.Payload.Request.PostReq;

import java.util.List;

public interface LikesService {
    int likeUnlikePost(PostLikeReq postLikeReq, AppUser user);

    int fetchAllPostLikes(Long postId);
}
