package com.example.emaillogtime.reposiotry;

import com.example.emaillogtime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select l.mail from User l")
    List<String> getAllEmail();

    User findUserByMail(String email);
}
