package com.spinetracker.spinetracker.domain.posture.query.application.service;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.WeeklyDashBoardPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.domain.service.CalcDashboardPostureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardPostureLogService {

    private final FindPostureLogService findPostureLogService;
    private final CalcDashboardPostureLogService calcDashboardPostureLogService;

    @Autowired
    public DashboardPostureLogService(FindPostureLogService findPostureLogService, CalcDashboardPostureLogService calcDashboardPostureLogService) {
        this.findPostureLogService = findPostureLogService;
        this.calcDashboardPostureLogService = calcDashboardPostureLogService;
    }

    public List<WeeklyDashBoardPostureLogDTO> getWeeklyDashBoard(Long memberId) {
        List<FindPostureLogDTO> weeklyPostureLogList = findPostureLogService.findWeeklyByMemberId(memberId);

        return calcDashboardPostureLogService.calculate(weeklyPostureLogList);
    }
}
