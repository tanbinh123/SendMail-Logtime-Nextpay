package com.example.emaillogtime.security;

import com.example.emaillogtime.entity.Account;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
//@Getter
//@Setter
public class CustomUserDetails implements UserDetails {

//    private Account account;

    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomerUserDetails(Account account){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.username = account.getUsername();
        customUserDetails.password = account.getPassword();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(account.getUsername()));
//        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRole().getName()));
        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return account.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE"+role))
//                .collect(Collectors.toList());
//    }

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
