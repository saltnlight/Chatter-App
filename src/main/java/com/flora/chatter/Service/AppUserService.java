package com.flora.chatter.Service;


import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.LoginReq;
import com.flora.chatter.Payload.Request.RegisterReq;

public interface AppUserService{
    public AppUser createUser(RegisterReq registerUserReq);

    public AppUser loginUser(LoginReq loginUserReq);
}
