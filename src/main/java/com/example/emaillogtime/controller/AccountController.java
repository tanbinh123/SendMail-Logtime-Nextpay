package com.example.emaillogtime.controller;

import com.example.emaillogtime.dto.*;
import com.example.emaillogtime.entity.Account;
import com.example.emaillogtime.reposiotry.AccountRepository;
import com.example.emaillogtime.reposiotry.EntriesTimeDtoRepository;
import com.example.emaillogtime.security.jwt.JwtProvider;
import com.example.emaillogtime.service.AccountService;
import com.example.emaillogtime.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Random;

@ComponentScan
@RestController
@RequestMapping(value = "account/v1")
@Slf4j
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EntriesTimeDtoRepository entriesTimeDtoRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

//    @PostMapping()
//    public ResponseEntity<EntriesTimeDTO> createEntriesTimeDTO (@RequestBody EntriesTimeDTO entriesTimeDTO) {
//        return ResponseEntity.status(HttpStatus.OK).body(entriesTimeDtoRepository.save(entriesTimeDTO));
//    }

    @GetMapping("/get")
    public String greeting() {
        return "greeting ok";
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateEntriesTimeDTO(@Valid @PathVariable(name = "id") Long entriesTimeDtoId,
                                                               @RequestBody(required = false) EntriesTimeDTO entriesTimeDTO) {
        Optional<EntriesTimeDTO> entriesTimeDTO1 = entriesTimeDtoRepository.findById(entriesTimeDtoId);
        if (entriesTimeDTO1.isPresent()) {
            entriesTimeDTO1.get().setTimeWeekDto(entriesTimeDTO.getTimeWeekDto());
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject("True","Cap nhat gio thanh cong",entriesTimeDtoRepository.save(entriesTimeDTO1.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ResponseObject("False","Khong tim thay EntriesTimeID",""));
        }
    }

    @PostMapping(value = "/create-account")
    ResponseEntity<ResponseObject> createUser(@Valid @RequestBody Account account) {
        Optional<Account> account1 = accountRepository.findAccountByUsername(account.getUsername());
        if (account1.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject("False", "Username already", "",""));
        } else {
            String password = account.getPassword().trim();
            String passwordEncoder = bCryptPasswordEncoder.encode(password).trim();
            account.setPassword(passwordEncoder);
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject("OK", "Successfully","", accountService.save(account)));
        }
    }

    @PutMapping("/change-password")
    ResponseEntity<ResponseObject> changePassword(@Valid @RequestBody AccountChangePassword accountChangePassword) {
        Optional<Account> account = accountRepository.findById(accountChangePassword.getId());
        if (account.isPresent()) {
            if (bCryptPasswordEncoder.matches(accountChangePassword.getPasswordOld(), account.get().getPassword())) {
                String password1 = accountChangePassword.getPasswordNew();
                String passwordEncoder1 = bCryptPasswordEncoder.encode(password1);
//            String password = accountChangePassword.getPasswordNew();
//            String passwordEncoder = bCryptPasswordEncoder.encode(password);
                account.get().setPassword(passwordEncoder1);
                accountRepository.save(account.get());
                return ResponseEntity.status(HttpStatus.OK).body(new
                        ResponseObject("OK", "change password ok", account));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                        ResponseObject("False", "password old incorrect", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
                    ResponseObject("False", "account does not exit", ""));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseObject> forgotPassword(@Valid @RequestBody AccountForgotPassword accountForgotPassword) throws MessagingException {
        Optional<Account> account = accountRepository.findAccountByUsername(accountForgotPassword.getUsername());
        if (account.isPresent()) {

            String password =  RandomStringUtils.randomAscii(10); // random pass 6 so
//            account.get().setPassword(password);
            String passwordEncoder = bCryptPasswordEncoder.encode(password);
            account.get().setPassword(passwordEncoder);
            accountRepository.save(account.get());
            DataMailDTO mailDTO = new DataMailDTO();
            mailDTO.setTo(account.get().getUsername());
            mailDTO.setSubject("Forgot Password");
            mailDTO.setContent("Your password :" + password /*account.get().getPassword()*/); // send mail new pass
            mailService.sendMail(mailDTO, "client");
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject("OK", "Password send by email", "", ""));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject("False", "username not found", "", ""));
        }
    }

    @GetMapping(value = "/login")
    ResponseEntity<ResponseObject> loginAccount(@Valid @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findAccountByUsername(account.getUsername());
        if (accountOptional.isPresent()) {
//           if (accountOptional.get().getPassword().equals(account.getPassword())){
            if (bCryptPasswordEncoder.matches(account.getPassword(), accountOptional.get().getPassword())) {
                String token = jwtProvider.generateToken(account.getUsername());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "login successfully",  token, accountOptional));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("False", "Password wrong", "", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("False", "wrong username", "", ""));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

}
