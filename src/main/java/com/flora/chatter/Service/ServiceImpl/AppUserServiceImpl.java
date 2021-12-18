package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Exception.UserException;
import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.LoginReq;
import com.flora.chatter.Payload.Request.RegisterReq;
import com.flora.chatter.Repository.AppUserRepository;
import com.flora.chatter.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public AppUser createUser(RegisterReq registerUserReq) {
        if(appUserRepository.existsAppUserByEmail(registerUserReq.getEmail())){
            throw new UserException("Already Exists");
        }
        AppUser user = new AppUser();
        user.setEmail(registerUserReq.getEmail());
        user.setFirstName(registerUserReq.getFirstName());
        user.setLastName(registerUserReq.getLastName());
        user.setGender(registerUserReq.getGender());
        user.setMobile(registerUserReq.getMobile());
        user.setPassword(registerUserReq.getPassword());

        return appUserRepository.save(user);
    }

    @Override
    @Transactional
    public AppUser loginUser(LoginReq loginUserReq) {
        AppUser user = appUserRepository.findByEmail(loginUserReq.getEmail());
        if(!ObjectUtils.isEmpty(user)){
            boolean isPassword = loginUserReq.getPassword().equals(user.getPassword());
            if (isPassword) return user;
            else throw new UserException("Invalid Password");
        }
        throw new UserException("User not found");
    }
}
