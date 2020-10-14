package com.phraseup.phupserver.service;

public interface LogInService {

    boolean authorizeLogInRequest(String username, String password);
}
