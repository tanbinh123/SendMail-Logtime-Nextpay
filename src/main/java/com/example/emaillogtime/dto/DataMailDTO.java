package com.example.emaillogtime.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMailDTO {

    private String to;
    private String subject;
    private String content;
//    private float gioThieu;
    private Map<String, Object> objectMap;

}
