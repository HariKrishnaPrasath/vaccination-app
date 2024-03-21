package com.jpa.vaccinationapp.login.service;

import com.jpa.vaccinationapp.login.Login;

import javax.security.auth.login.LoginException;

public interface LoginService {
    Integer checkLogin(Login login) throws LoginException;
}
