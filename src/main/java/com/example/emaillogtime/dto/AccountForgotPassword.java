package com.example.emaillogtime.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountForgotPassword {
//    @Valid
    private String username;
//    private Map<String, Object> objectMap;
}
