package com.spinetracker.spinetracker.domain.posture.query.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FindDailyPostureLogServiceTest {

    @Autowired
    private FindDailyPostureLogService findDailyPostureLogService;

    @Test
    @DisplayName("사용자 Id를 통해 오늘의 자세 요약 정보 확인 테스트")
    void testGetSummary() {

        Long memberId = 8L;
        Assertions.assertDoesNotThrow(
                () -> findDailyPostureLogService.getSummary(memberId)
        );
    }
}