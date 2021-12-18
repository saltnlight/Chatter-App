package com.flora.chatter.Service;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Comment;
import com.flora.chatter.Payload.Request.CommentReq;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentService {
    Comment createComment(CommentReq commentReq, AppUser user);

    boolean deleteComment(Long id);

    List<Comment> fetchAllCommentPerPost(Long id);

    @Transactional
    Comment updateComment(CommentReq commentReq, AppUser user);
}
