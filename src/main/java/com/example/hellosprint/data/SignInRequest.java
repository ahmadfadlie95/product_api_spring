package com.example.hellosprint.data;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;
}

