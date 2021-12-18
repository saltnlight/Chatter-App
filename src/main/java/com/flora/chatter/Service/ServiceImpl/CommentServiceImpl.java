package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Comment;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Payload.Request.CommentReq;
import com.flora.chatter.Repository.CommentRepository;
import com.flora.chatter.Repository.PostRepository;
import com.flora.chatter.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Comment createComment(CommentReq commentReq, AppUser user) {
        Comment comment = new Comment();
        comment.setUser(user);
        Post post = getPostById(commentReq.getPostID());
        comment.setPost(post);
        comment.setBody(commentReq.getBody());
        Comment newComment = commentRepository.save(comment);
        return newComment;
    }

    private Post getPostById(Long postId){
        return postRepository.findById(postId).get();
    }

    @Override
    public boolean deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            commentRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public List<Comment> fetchAllCommentPerPost(Long id) {
        return commentRepository.findCommentsByPostIdOrderByCreatedAtDesc(id);
    }

    @Override
    @Transactional
    public Comment updateComment(CommentReq commentReq, AppUser user) {
        Optional<Comment> comment = commentRepository.findById(commentReq.getCommentID());
        if (comment.isPresent()){
            Comment commentToBeUpdated = comment.get();
            commentToBeUpdated.setUser(user);
            var post = postRepository.findById(commentReq.getPostID());
            commentToBeUpdated.setPost(post.get());
            commentToBeUpdated.setBody(commentReq.getBody());
//            return commentToBeUpdated;
            return commentRepository.save(commentToBeUpdated);
        }else{
            System.out.println("I don't know how this would even be possible");
            return null;
        }
    }
}
