package com.proppay.platform.pay.application.in.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
}

