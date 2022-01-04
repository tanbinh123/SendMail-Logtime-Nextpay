package com.example.emaillogtime.service.impl;

import com.example.emaillogtime.reposiotry.EntriesTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntriesTimeService implements com.example.emaillogtime.service.EntriesTimeService {
    @Autowired
    private EntriesTimeRepository entriesTimeRepository;
}
