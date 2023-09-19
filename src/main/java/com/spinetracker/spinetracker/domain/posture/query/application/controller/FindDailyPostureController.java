package com.spinetracker.spinetracker.domain.posture.query.application.controller;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.service.FindDailyPostureLogService;
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
import org.springframework.web.bind.annotation.*;


@Tag(name = "PostureLog", description = "자세 기록 API")
@RestController
@RequestMapping("/posture")
public class FindDailyPostureController {

    private final FindDailyPostureLogService findDailyPostureLogService;

    @Autowired
    public FindDailyPostureController(FindDailyPostureLogService findDailyPostureLogService) {
        this.findDailyPostureLogService = findDailyPostureLogService;
    }

    @Operation(
            summary = "오늘의 자세 기록 Summary 조회",
            description = "토큰을 기반으로 사용자의 오늘의 자세 기록 Summary를 조회합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = DailyPostureLogDTO.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @GetMapping("/daily")
    public ResponseEntity<DailyPostureLogDTO> getDailyPostureLog(@CurrentMember UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();
        DailyPostureLogDTO dailyPostureLogDTO = findDailyPostureLogService.getSummary(memberId);
        return ResponseEntity.ok(dailyPostureLogDTO);
    }
}
