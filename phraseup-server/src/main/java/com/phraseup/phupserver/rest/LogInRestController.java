package com.phraseup.phupserver.rest;

import com.phraseup.phupserver.service.LogInService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
class LogInData {
    private final String username;
    private final String password;

    @Autowired
    LogInData(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

@RestController
@RequestMapping("/login/")
public class LogInRestController {

    private final LogInService logInService;

    @Autowired
    public LogInRestController(@Qualifier("logInServiceImpl") LogInService logInService) {
        this.logInService = logInService;
    }

    @PostMapping("/")
    boolean logInAuthorization(@RequestBody LogInData logInData) {
        return logInService.authorizeLogInRequest(logInData.getUsername(), logInData.getPassword());
    }
}
