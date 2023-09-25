package com.spinetracker.spinetracker.domain.posture.command.application.service;

import com.spinetracker.spinetracker.domain.posture.command.domain.service.RequestPostFcmMessage;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.service.FindDailyPostureLogService;
import com.spinetracker.spinetracker.infra.firebase.query.application.dto.FindFcmTokenDTO;
import com.spinetracker.spinetracker.infra.firebase.query.application.service.FindFcmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostPostureLogMessageService {

    private final FindFcmTokenService findFcmTokenService;
    private final RequestPostFcmMessage requestPostFcmMessage;
    private final FindDailyPostureLogService findDailyPostureLogService;

    @Autowired
    public PostPostureLogMessageService(FindFcmTokenService findFcmTokenService, RequestPostFcmMessage requestPostFcmMessage, FindDailyPostureLogService findDailyPostureLogService) {
        this.findFcmTokenService = findFcmTokenService;
        this.requestPostFcmMessage = requestPostFcmMessage;
        this.findDailyPostureLogService = findDailyPostureLogService;
    }

    @Transactional
    public void postDailyPostureLogMessage(Long memberId) {
        FindFcmTokenDTO findFcmTokenDTO = findFcmTokenService.findByMemberId(memberId);
        System.out.println("findFcmTokenDTO = " + findFcmTokenDTO);
        DailyPostureLogDTO dailyPostureLogDTO = findDailyPostureLogService.getSummary(memberId);
        System.out.println("dailyPostureLogDTO = " + dailyPostureLogDTO);
        requestPostFcmMessage.send("오늘의 자세 요약", dailyPostureLogDTO.toString(), findFcmTokenDTO.getFcmToken());
    }
}
