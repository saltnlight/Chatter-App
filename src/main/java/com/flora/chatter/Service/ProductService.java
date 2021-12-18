package com.flora.chatter.Service;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Model.Post;
import com.flora.chatter.Model.Product;
import com.flora.chatter.Payload.Request.PostReq;

import java.util.List;

public interface ProductService {
    Product createProduct(PostReq postReq, AppUser user);

    Product updateProduct(PostReq postReq, AppUser user);


}
