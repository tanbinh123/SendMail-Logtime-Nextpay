package com.example.emaillogtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class EmailLogtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailLogtimeApplication.class, args);
//        Logtime logtime = new Logtime();
//        System.out.println(logtime.getGioThieu());
    }

    public PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance();
    }

}
