package com.example.emaillogtime.security;

import com.example.emaillogtime.entity.Account;
import com.example.emaillogtime.reposiotry.AccountRepository;
import com.example.emaillogtime.reposiotry.UserRepository;
import com.example.emaillogtime.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountService.findAccountByUsername(username);
        return CustomUserDetails.fromUserEntityToCustomerUserDetails(account.get());
//        CustomUserDetails customUserDetails = new CustomUserDetails();
//        if (account.isPresent()) {
//            customUserDetails.setAccount(account.get());
//        } else {
//            throw new UsernameNotFoundException("User not exits with username " + username);
//        }
//        return null;
    }
}
