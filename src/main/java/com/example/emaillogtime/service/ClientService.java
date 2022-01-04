package com.example.emaillogtime.service;

//import com.example.emaillogtime.dto.sdt.ClientSdi;

import com.example.emaillogtime.dto.DataMailDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Boolean create(DataMailDTO dataMailDTO);
    Boolean createTuan(DataMailDTO dataMailDTO);
}
