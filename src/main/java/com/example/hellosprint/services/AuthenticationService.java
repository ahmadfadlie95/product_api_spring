package com.example.hellosprint.services;

import com.example.hellosprint.data.JwtAuthenticationResponse;
import com.example.hellosprint.data.SignInRequest;
import com.example.hellosprint.data.SignUpRequest;
import com.example.hellosprint.models.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
