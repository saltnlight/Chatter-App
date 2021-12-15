package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Exception.UserException;
import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Payload.Request.LoginReq;
import com.flora.chatter.Payload.Request.RegisterReq;
import com.flora.chatter.Repository.AppUserRepository;
import com.flora.chatter.Service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class AppUserServiceTest {
    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserServiceImpl appUserService;

    private AppUser appUser = new AppUser();
    private RegisterReq registerReq = new RegisterReq();
    private LoginReq loginReq = new LoginReq();

    @BeforeEach
    void setUp() {
        registerReq.setFirstName("Kenny");
        registerReq.setLastName("Ojeola");
        registerReq.setMobile("08166062243");
        registerReq.setGender("2");
        registerReq.setEmail("kenOjeola@mail.com");
        registerReq.setPassword("newuser");
    }

    @Test
    void createUser() {
        lenient().when(appUserRepository.existsAppUserByEmail(anyString())).thenReturn(true);
        when(appUserRepository.save(any(AppUser.class))).thenReturn(appUser);
//        var actual = appUserService.createUser(registerReq);
//        assertNull(actual);
//        when(appUserRepository.existsAppUserByEmail(anyString())).thenReturn(true);
        assertThrows(UserException.class,
                () ->{
                    appUserService.createUser(registerReq);
                }
        );

    }

    @Test
    void loginUser() {
    }
}