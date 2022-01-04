package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.dto.DataMailDTO;
import com.example.emaillogtime.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(DataMailDTO dataMail, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        Context context = new Context();
        context.setVariables(dataMail.getObjectMap());

        String html = templateEngine.process(templateName, context);

        helper.setTo(dataMail.getTo());
        helper.setSubject(dataMail.getSubject());
//        helper.setText(html,true);
        helper.setText(dataMail.getContent());

//        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
//                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        message.setContent(dataMail.getContent(),"text/html");
        mailSender.send(message);
    }
}
