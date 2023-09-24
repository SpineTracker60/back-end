package com.spinetracker.spinetracker.infra.firebase.command.application.service;

import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.FCMToken;
import com.spinetracker.spinetracker.infra.firebase.command.domain.repository.FcmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateFcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;

    @Autowired
    public UpdateFcmTokenService(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    public FCMToken update(Long memberId, String FcmToken) {
        Optional<FCMToken> optionalFCMToken = fcmTokenRepository.findByMember_Id(memberId);
        if(optionalFCMToken.isPresent()) {
            FCMToken fcmToken = optionalFCMToken.get();
            fcmToken.setToken(FcmToken);
            fcmTokenRepository.save(fcmToken);
            return fcmToken;
        }
        return null;
    }
}
