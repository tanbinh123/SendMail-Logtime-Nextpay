package com.example.emaillogtime.security;

import com.example.emaillogtime.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/account/v1/get").permitAll()
                .antMatchers("/account/v1/login").permitAll()
                .antMatchers("/account/v1/forgot-password").permitAll()

                .anyRequest().authenticated().and()
//                .antMatchers("/account/v1/logout").permitAll()
//                .antMatchers("/account/v1/forgot-password").permitAll()
//                .antMatchers("/account/v1/login").permitAll()
//                .antMatchers("/account/v1/{id}").permitAll()
//                .antMatchers("/account/v1/create").permitAll()
////                .antMatchers("/account/v1/{id}").hasRole("ADMIN")
////                .antMatchers("/account/v1/create").hasRole("ADMIN")
//                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


//        http
//                .cors().and()
//                .csrf().disable() // tan cong xac thuc tren trang  khac
//                .authorizeRequests()
//                .antMatchers("/account/v1/get").permitAll()
//                .antMatchers("/account/v1/logout").permitAll() // cho phep di vao api /login
//                .anyRequest().authenticated().and() // tat ca cac request con lai phai duoc chung thuc;
//                .httpBasic().and()
//                .formLogin().loginPage("/account/v1/login").permitAll()
//               /* .defaultSuccessUrl("/account/v1/create")
//                .loginProcessingUrl("/j_spring_security_check")
//                .and()
//                .logout().logoutSuccessUrl("/account/v1/logout").permitAll()*/;
    }
}
