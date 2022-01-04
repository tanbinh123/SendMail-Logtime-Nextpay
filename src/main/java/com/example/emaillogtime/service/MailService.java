package com.example.emaillogtime.service;

import com.example.emaillogtime.dto.DataMailDTO;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


public interface MailService {
    void sendMail(DataMailDTO dataMail, String templateName) throws MessagingException;


}
