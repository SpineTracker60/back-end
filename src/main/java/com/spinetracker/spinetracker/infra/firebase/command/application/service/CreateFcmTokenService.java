package com.spinetracker.spinetracker.infra.firebase.command.application.service;

import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.FCMToken;
import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.vo.MemberVO;
import com.spinetracker.spinetracker.infra.firebase.command.domain.repository.FcmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFcmTokenService {

    private final FcmTokenRepository fcmTokenRepository;

    @Autowired
    public CreateFcmTokenService(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    public FCMToken create(Long memberId, String FcmToken) {

        return fcmTokenRepository.save(
                new FCMToken(new MemberVO(memberId), FcmToken)
        );
    }
}
