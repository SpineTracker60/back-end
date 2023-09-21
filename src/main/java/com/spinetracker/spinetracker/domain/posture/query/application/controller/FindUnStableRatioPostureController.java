package com.spinetracker.spinetracker.domain.posture.query.application.controller;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.UnStableRatioDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.service.FindUnstableRatioPostureLogService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "PostureLog", description = "자세 기록 API")
@RestController
@RequestMapping("/posture")
public class FindUnStableRatioPostureController {

    private final FindUnstableRatioPostureLogService findUnstableRatioPostureLogService;

    @Autowired
    public FindUnStableRatioPostureController(FindUnstableRatioPostureLogService findUnstableRatioPostureLogService) {
        this.findUnstableRatioPostureLogService = findUnstableRatioPostureLogService;
    }

    @Operation(
            summary = "주간 불안정 자세 비율 조회",
            description = "토큰을 기반으로 사용자의 주간 불안정 자세 비율을 조회합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UnStableRatioDTO.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @GetMapping("/ratio")
    public ResponseEntity<UnStableRatioDTO> getWeeklyUnstablePostureLog(@RequestParam Long memberId) {
        UnStableRatioDTO weeklyUnStableRatioDTO = findUnstableRatioPostureLogService.getUnStableRatio(memberId);
        return ResponseEntity.ok(weeklyUnStableRatioDTO);
    }
}
