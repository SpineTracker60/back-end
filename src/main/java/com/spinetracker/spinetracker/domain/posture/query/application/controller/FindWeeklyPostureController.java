package com.spinetracker.spinetracker.domain.posture.query.application.controller;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.WeeklyDashBoardPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.service.DashboardPostureLogService;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PostureLog", description = "자세 기록 API")
@RestController
@RequestMapping("/posture")
public class FindWeeklyPostureController {


    private final DashboardPostureLogService dashboardPostureLogService;

    @Autowired
    public FindWeeklyPostureController(DashboardPostureLogService dashboardPostureLogService) {
        this.dashboardPostureLogService = dashboardPostureLogService;
    }


    @Operation(
            summary = "주간 자세 기록 Summary 조회",
            description = "토큰을 기반으로 사용자의 주간 자세 기록 Summary를 조회합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = WeeklyDashBoardPostureLogDTO.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @GetMapping("/dashboard/weekly")
    public ResponseEntity<List<WeeklyDashBoardPostureLogDTO>> getWeeklyPostureLog(@CurrentMember UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();
        List<WeeklyDashBoardPostureLogDTO> weeklyDashBoardPostureLogDTOList = dashboardPostureLogService.getWeeklyDashBoard(memberId);
        return ResponseEntity.ok(weeklyDashBoardPostureLogDTOList);
    }
}
