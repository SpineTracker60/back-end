package com.spinetracker.spinetracker.domain.posture.query.application.service;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.UnStableRatioDTO;
import com.spinetracker.spinetracker.domain.posture.query.domain.service.CalcUnStableRatioPostureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUnstableRatioPostureLogService {

    private final FindPostureLogService findPostureLogService;
    private final CalcUnStableRatioPostureLogService calcUnStableRatioPostureLogService;

    @Autowired
    public FindUnstableRatioPostureLogService(FindPostureLogService findPostureLogService, CalcUnStableRatioPostureLogService calcUnStableRatioPostureLogService) {
        this.findPostureLogService = findPostureLogService;
        this.calcUnStableRatioPostureLogService = calcUnStableRatioPostureLogService;
    }

    public UnStableRatioDTO getUnStableRatio(Long memberId) {
        List<FindPostureLogDTO> weeklyPostureLogList = findPostureLogService.findWeeklyByMemberId(memberId);
        return calcUnStableRatioPostureLogService.calculate(weeklyPostureLogList);
    }
}
