package com.flora.chatter.Service;


import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.LoginReq;
import com.flora.chatter.Payload.Request.RegisterReq;

public interface AppUserService{
    AppUser createUser(RegisterReq registerUserReq);

    AppUser loginUser(LoginReq loginUserReq);
}
