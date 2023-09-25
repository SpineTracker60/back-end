package com.spinetracker.spinetracker.infra.firebase.query.application.service;

import com.spinetracker.spinetracker.infra.firebase.query.application.dto.FindFcmTokenDTO;
import com.spinetracker.spinetracker.infra.firebase.query.domain.repository.FcmTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindFcmTokenService {

    private final FcmTokenMapper fcmTokenMapper;

    @Autowired
    public FindFcmTokenService(FcmTokenMapper fcmTokenMapper) {
        this.fcmTokenMapper = fcmTokenMapper;
    }

    public FindFcmTokenDTO findByMemberId(Long memberId) {
        return fcmTokenMapper.findByMemberId(memberId);
    }
}
