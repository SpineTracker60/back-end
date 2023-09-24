package com.spinetracker.spinetracker.infra.firebase.infra.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@InfraService
public class GetAccessTokenService {
    @Value("${fcm.certification}")
    private String googleApplicationCredentials;

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = { MESSAGING_SCOPE };

    public String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("service-account.json"))
                .createScoped(Arrays.asList(SCOPES));
        googleCredentials.refreshAccessToken();
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
