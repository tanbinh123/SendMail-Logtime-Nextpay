package com.example.emaillogtime.scheduler;

import com.example.emaillogtime.dto.DataMailDTO;
import com.example.emaillogtime.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
@Slf4j
public class Scheduler {
    @Autowired
    private ClientService clientService;
    DataMailDTO dataMailDTO = new DataMailDTO();
//
////    @Bean
//    @Scheduled(cron = "*/15 * * * * *") // 10s // 0 0 17 ? * MON-FRI -> 17h thu 2 - thu 6 send mail
//    public void reportCurrentTime() { // 0 0 17 * * ? -> 17h hang ngay send mail
//        clientService.create(dataMailDTO); // 0 0 11 ? * SAT -> send mail vao luc 11h thu 7 hang tuan
////        clientService.createTuan(dataMailDTO);
//    }
//
////    @Bean
//    @Scheduled(cron = "*/30 * * * * *")
//    public void reportCurrentTimeOne() {
//        clientService.createTuan(dataMailDTO);
//    }

}
