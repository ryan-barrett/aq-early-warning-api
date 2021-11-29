package com.ryan.capstone.aq.aqearlywarning.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ryan.capstone.aq.aqearlywarning.domain.firebase.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendNotification(Note note) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setTopic(note.getTopic())
                .setNotification(notification)
                .putAllData(note.getData())
                .build();

        var status = firebaseMessaging.send(message);
        logger.debug(status);
    }

}
