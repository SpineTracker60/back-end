package com.spinetracker.spinetracker.domain.posture.query.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DashboardPostureLogServiceTest {

    @Autowired
    private DashboardPostureLogService dashboardPostureLogService;

    @Test
    @DisplayName("사용자 Id를 통해 주간 대시보드 조회 테스트")
    void getWeeklyDashBoard() {

        Long memberId = 8L;
        System.out.println(dashboardPostureLogService.getWeeklyDashBoard(memberId));

    }
}