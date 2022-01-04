package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.entity.Account;
//import com.example.emaillogtime.entity.Role;
import com.example.emaillogtime.reposiotry.AccountRepository;
//import com.example.emaillogtime.reposiotry.RoleRepository;
import com.example.emaillogtime.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public Optional<Account> findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Account save(Account account) {
//        Role role = roleRepository.findByName("ROLE_USER");
//        account.setRole(role);
        return accountRepository.save(account);
    }
}
