package com.ryan.capstone.aq.aqearlywarning.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ryan.capstone.aq.aqearlywarning.domain.firebase.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final FirebaseMessagingService firebaseMessagingService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String subject = "AQ Alert";

    public enum Notification {
        DANGER {
            public String message() {
                return "The air quality has exceeded your currently set threshold.";
            }
        },
        SAFE {
            public String message() {
                return "The air quality has returned to safe levels.";
            }
        };

        public abstract String message();
    }

    public NotificationService(@Autowired FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    public boolean sendPushNotification(String topic, String message) {
        logger.info("sending push notification: topic - " + topic + " message - " + message);
        return sendIosPushNotification(topic, message);
    }

    private boolean sendIosPushNotification(String topic, String message) {
        try {
            logger.info("sending ios push notification: topic - " + topic + " message - " + message);
            firebaseMessagingService.sendNotification(new Note(subject, message, topic));
            return true;
        } catch (FirebaseMessagingException exception) {
            logger.error(exception.getMessage());
            return false;
        }
    }
}
