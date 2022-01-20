package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Model.AppUser;
import com.flora.chatter.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsServImpl implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException(username));
//        return new MyUserDetailsImpl(user.get());

        return user.map(MyUserDetailsImpl::new).get();
    }
}
