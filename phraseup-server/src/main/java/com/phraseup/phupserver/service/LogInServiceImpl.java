package com.phraseup.phupserver.service;

import com.phraseup.phupserver.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {

    private final UserDAO userDAO;

    @Autowired
    public LogInServiceImpl(@Qualifier("userDAOJPAImpl") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authorizeLogInRequest(String username, String password) {
        return userDAO.authorizeLogInRequest(username, password);
    }
}
