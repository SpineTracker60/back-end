package com.spinetracker.spinetracker.domain.posture.query.application.service;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.domain.service.CalcDailyPostureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDailyPostureLogService {


    private final FindPostureLogService findPostureLogService;
    private final CalcDailyPostureLogService calcDailyPostureLogService;

    @Autowired
    public FindDailyPostureLogService(FindPostureLogService findPostureLogService, CalcDailyPostureLogService calcDailyPostureLogService) {
        this.findPostureLogService = findPostureLogService;
        this.calcDailyPostureLogService = calcDailyPostureLogService;
    }

    public DailyPostureLogDTO getSummary(Long memberId) {
        List<FindPostureLogDTO> todayPostureLogList = findPostureLogService.findByMemberId(memberId);
        return calcDailyPostureLogService.calculate(todayPostureLogList);
    }
}
