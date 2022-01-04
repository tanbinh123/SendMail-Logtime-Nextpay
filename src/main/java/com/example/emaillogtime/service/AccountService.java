package com.example.emaillogtime.service;

import com.example.emaillogtime.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findAccountByUsername(String username);
    Account save(Account account);
}
