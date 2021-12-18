package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Exception.PostException;
import com.flora.chatter.Exception.UserException;
import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Model.Product;
import com.flora.chatter.Payload.Request.PostReq;
import com.flora.chatter.Repository.CommentRepository;
import com.flora.chatter.Repository.PostLikesRepository;
import com.flora.chatter.Repository.PostRepository;
import com.flora.chatter.Repository.ProductRepository;
import com.flora.chatter.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PostRepository pr, PostLikesRepository postLikesRepository, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.postRepository = pr;
        this.postLikesRepository = postLikesRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Product createProduct(PostReq postReq, AppUser user) {
        log.info("user in createProduct {}", user);
        Product product = new Product();
        product.setImg(postReq.getImg());
        product.setBody(postReq.getBody());
        product.setPrice(postReq.getPrice());
        product.setDescription(postReq.getDescription());
        product.setIsPersonal(postReq.getIsPersonal());
        product.setIsProduct(postReq.getIsProduct());
        product.setUser(user);
        Product savedProduct;
        try{
            savedProduct = productRepository.save(product);
        }catch (UserException e){
            throw new UserException("Can not create this product");
        }
        return savedProduct;
    }

    @Override
    @Transactional
    public Product updateProduct(PostReq postReq, AppUser user) {
        Optional<Post> post = postRepository.findById(postReq.getId());
        Optional<Product> product = productRepository.findById(post.get().getId());

        Product updatedProduct = new Product();
        try{
            if (product.isPresent()){// updating a product
                updatedProduct = product.get();
            }
            else{// updating a post to product
                updatedProduct.setUser(post.get().getUser());
                updatedProduct.setNumOfLikes(post.get().getNumOfLikes());
                updatedProduct.setNumOfComments(post.get().getNumOfComments());
            }
            updatedProduct.setImg(postReq.getImg());
            updatedProduct.setBody(postReq.getBody());
            updatedProduct.setDescription(postReq.getDescription());
            updatedProduct.setPrice(postReq.getPrice());
            updatedProduct.setIsPersonal(postReq.getIsPersonal());
            updatedProduct.setIsProduct(postReq.getIsProduct());
            return updatedProduct;
        }catch(PostException e){
            log.error("I don't know how this would even be possible");
            throw new PostException("Can not update a product that doesn't exist");
        }
//        return updatedProduct;
    }
}
