package com.ryan.capstone.aq.aqearlywarning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean sendIosPushNotification(String message) {
        logger.info("sending ios push notification " + message);
        return true;
    }
}
