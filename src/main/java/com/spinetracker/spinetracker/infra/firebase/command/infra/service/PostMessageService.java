package com.spinetracker.spinetracker.infra.firebase.command.infra.service;

import com.google.firebase.messaging.*;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Autowired;


@InfraService
public class PostMessageService {

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public PostMessageService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public boolean sendToToken(String title, String body, String receiverToken) {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "YOUR_REGISTRATION_TOKEN";

        // See documentation on defining a message payload.
        Notification notification = Notification.builder()
                .setTitle(title)
                .setImage("https://avatars.githubusercontent.com/u/19159759?s=400&u=0e0cc9ab7108cf605ef31085ba9451ec4dfb89e5&v=4")
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setToken(receiverToken)
                .setNotification(notification)
                // .putAllData(requestDto.getData())
                .build();

        try {
            // Send a message to the device corresponding to the provided
            // registration token.
            String response = firebaseMessaging.send(message);
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + response);
            // [END send_to_token]
            return true;
        } catch (FirebaseMessagingException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
