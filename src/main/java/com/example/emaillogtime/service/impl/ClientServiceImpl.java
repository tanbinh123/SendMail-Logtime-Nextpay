package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.dto.DataMailDTO;
//import com.example.emaillogtime.dto.sdt.ClientSdi;
import com.example.emaillogtime.dto.EntriesTimeDTO;
import com.example.emaillogtime.entity.EntriesTime;
import com.example.emaillogtime.entity.User;
import com.example.emaillogtime.reposiotry.EntriesTimeDtoRepository;
import com.example.emaillogtime.reposiotry.EntriesTimeRepository;
import com.example.emaillogtime.reposiotry.UserRepository;
import com.example.emaillogtime.service.ClientService;
import com.example.emaillogtime.service.MailService;
//import com.example.emaillogtime.utils.Const;
//import com.example.emaillogtime.utils.DataUtils;
import com.example.emaillogtime.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntriesTimeRepository entriesTimeRepository;

    @Autowired
    EntriesTimeDtoRepository entriesTimeDtoRepository;

    @Override
    public Boolean create(DataMailDTO dataMailDTO) {
        User user = new User();
        EntriesTime entriesTime = new EntriesTime();
        try {
            List<String> listEmail = userRepository.getAllEmail();
            for (String email : listEmail) {
                user = userRepository.findUserByMail(email);
//                List<Float> hours = entries_timeRepository.getAllHoursByUserId(user.getUserId());
                List<Float> hours = entriesTimeRepository.getAllHoursAndAndDate(user.getUserId());
                float sum =0;
                for (Float hours1 : hours) {
                    sum += hours1;

                }
                if (sum < 8) {
                    dataMailDTO.setTo(email);
                    dataMailDTO.setSubject("NHẮC NHỞ LOGTIME");
                    dataMailDTO.setContent("Hom nay ban chua logtime du 8 tieng. so gio con thieu ngay hom nay:" + (8 - sum));
                    mailService.sendMail(dataMailDTO, "client");
                }
            }
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean createTuan(DataMailDTO dataMailDTO) {
        User user = new User();
        EntriesTime entriesTime = new EntriesTime();
        Float entriesTimeDTO = entriesTimeDtoRepository.getEntriesTimeDTO();
        try {
            List<String> listEmail = userRepository.getAllEmail();
            for (String email : listEmail) {
                user = userRepository.findUserByMail(email);
                List<Float> hours = entriesTimeRepository.getAllHoursAndAndDateOk(user.getUserId(), PageRequest.of(0, 3));
                float sum = 0;
                for (Float hours1 : hours) {
                    sum += hours1;
                }
                if (entriesTimeDTO == 44) {
                    if (sum < entriesTime.getSumHours()) {
                        entriesTime.setGioThieu(entriesTime.getSumHours() - sum);
                        dataMailDTO.setTo(email);
                        dataMailDTO.setSubject("NHẮC NHỞ LOGTIME");
//                    dataMailDTO.setContent("Giờ còn thiếu :" + ( logtime.getSum() - ((logtime.getLogTime()) * 5 + 4)));
                        dataMailDTO.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                                "<head>\n" +
                                "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>\n" +
                                "    <style>\n" +
                                "        body {\n" +
                                "            font-family: 'Roboto', sans-serif;\n" +
                                "            font-size: 48px;\n" +
                                "        }\n" +
                                "    </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<p>Dear Anh/Chi,</p>\n" +
                                "<p>Gui nhac nho logtime den anh chi: </p>\n" +
                                "<p>Tuan nay ban chua logtime du. Logtime di  </p>\n" +
                                "<p>So gio loctime hien tai cua ban la : " + sum + "</p>\n"+
                                "<p>So gio loctime toi thieu la : " + entriesTime.getSumHours() + "</p>\n"+
                                "<p>So gio logtime ban con thieu : " + (entriesTime.getSumHours() - sum) + "</p>\n" +
                                "<p>Day la email tu dong khong can tra loi dau</p>\n" +
                                "<h3>Im testing send a HTML email</h3>"
                                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>" +
                                "</body>\n" +
                                "</html>\n");
                        mailService.sendMail(dataMailDTO, "client");
                    }
                } else {
                    if (sum < entriesTimeDTO) {
                        entriesTime.setGioThieu(entriesTimeDTO - sum);
                        dataMailDTO.setTo(email);
                        dataMailDTO.setSubject("NHẮC NHỞ LOGTIME");
//                    dataMailDTO.setContent("Giờ còn thiếu :" + ( logtime.getSum() - ((logtime.getLogTime()) * 5 + 4)));
                        dataMailDTO.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                "<html xmlns:th=\"http://www.thymeleaf.org\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                                "<head>\n" +
                                "    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>\n" +
                                "    <style>\n" +
                                "        body {\n" +
                                "            font-family: 'Roboto', sans-serif;\n" +
                                "            font-size: 48px;\n" +
                                "        }\n" +
                                "    </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "<p>Dear Anh/Chi,</p>\n" +
                                "<p>Gui nhac nho logtime den anh chi: </p>\n" +
                                "<p>Tuan nay ban chua logtime du. Logtime di  </p>\n" +
                                "<p>So gio loctime hien tai cua ban la : " + sum + "</p>\n"+
                                "<p>So gio loctime toi thieu la : " + entriesTimeDTO + "</p>\n"+
                                "<p>So gio logtime ban con thieu : " + (entriesTimeDTO - sum) + "</p>\n" +
                                "<p>Day la email tu dong khong can tra loi dau</p>\n" +
                                "<h3>Im testing send a HTML email</h3>"
                                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>" +
                                "</body>\n" +
                                "</html>\n");
                        mailService.sendMail(dataMailDTO, "client");
                    }
                }
            }
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
