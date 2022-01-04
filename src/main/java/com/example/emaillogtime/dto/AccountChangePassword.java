package com.example.emaillogtime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountChangePassword {
    private Long id;
    private String passwordOld;
    @NotBlank(message = "mat khau khong duoc bo trong")
    @Size(min = 6, max = 255,message = "do dai mat khau phai co do dai tu 6-255 ky tu")
    private String passwordNew;
}
