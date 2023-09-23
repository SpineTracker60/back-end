package com.spinetracker.spinetracker.infra.firebase.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.JsonObject;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


@InfraService
public class PostMessageService {
    private final String PROJECT_ID = "spinetracker";
    private final String BASE_URL = "https://fcm.googleapis.com";
    private final String FCM_SEND_ENDPOINT = "/v1/projects/" + PROJECT_ID + "/messages:send";
    public final String MESSAGE_KEY = "message";

    private final GetAccessTokenService getAccessTokenService;

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public PostMessageService(GetAccessTokenService getAccessTokenService, FirebaseMessaging firebaseMessaging) {
        this.getAccessTokenService = getAccessTokenService;
        this.firebaseMessaging = firebaseMessaging;
    }

    public HttpURLConnection getConnection() throws IOException {
        // [START use_access_token]
        URL url = new URL(BASE_URL + FCM_SEND_ENDPOINT);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessTokenService.getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        return httpURLConnection;
        // [END use_access_token]
    }

    private String inputstreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    public boolean sendToToken(String title, String body, String receiverToken) throws FirebaseMessagingException {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "YOUR_REGISTRATION_TOKEN";

        // See documentation on defining a message payload.
        Notification notification = Notification.builder()
                .setTitle(title)
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



    public boolean sendMessage(JsonObject fcmMessage) throws IOException {
        HttpURLConnection connection = getConnection();
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(fcmMessage.toString());
        writer.flush();
        writer.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = inputstreamToString(connection.getInputStream());
            System.out.println("Message sent to Firebase for delivery, response:");
            System.out.println(response);
            return true;
        } else {
            System.out.println("Unable to send message to Firebase:");
            String response = inputstreamToString(connection.getErrorStream());
            System.out.println(response);
            return false;
        }
    }

    public boolean sendCommonMessage(String title, String body, String receiverToken) throws IOException {
        JsonObject notificationMessage = buildNotificationMessage(title, body);
        System.out.println("FCM request body for message using common notification object:");
        return sendMessage(notificationMessage);
    }

    private JsonObject buildNotificationMessage(String title, String body) {

        JsonObject jNotification = new JsonObject();
        jNotification.addProperty("title", title);
        jNotification.addProperty("body", body);

        JsonObject jMessage = new JsonObject();
        jMessage.add("notification", jNotification);

        JsonObject jFcm = new JsonObject();
        jFcm.add(MESSAGE_KEY, jMessage);

        return jFcm;
    }
}
