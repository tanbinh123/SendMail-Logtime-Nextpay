package com.example.emaillogtime.reposiotry;

import com.example.emaillogtime.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
   Optional<Account> findAccountByUsername(String username);
   Optional<Account> findAccountByPassword(String password);
}
