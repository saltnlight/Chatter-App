package com.flora.chatter.Service.ServiceImpl;

import com.flora.chatter.Model.AppUser;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Service
public class MyUserDetailsImpl implements UserDetails {
//    private AppUser user;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetailsImpl(AppUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = true;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("USER"));
//        this.authorities = Arrays.asList(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
