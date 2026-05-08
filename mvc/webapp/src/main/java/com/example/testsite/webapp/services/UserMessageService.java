package com.example.testsite.webapp.services;

import com.example.testsite.webapp.model.SupportUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageService implements MessageService {
    @Autowired
    SupportUserMessage userMessage;

    @Override
    public String createMessage(int userId) {
        userMessage.setUserId(userId);
        return userMessage.getMessage();
    }
}
