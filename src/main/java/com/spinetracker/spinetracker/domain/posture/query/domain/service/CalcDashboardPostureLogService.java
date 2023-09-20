package com.spinetracker.spinetracker.domain.posture.query.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.WeeklyDashBoardPostureLogDTO;
import com.spinetracker.spinetracker.global.common.annotation.DomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DomainService
public class CalcDashboardPostureLogService {

    private final CalcDailyPostureLogService calcDailyPostureLogService;

    @Autowired
    public CalcDashboardPostureLogService(CalcDailyPostureLogService calcDailyPostureLogService) {
        this.calcDailyPostureLogService = calcDailyPostureLogService;
    }

    public List<WeeklyDashBoardPostureLogDTO> calculate(List<FindPostureLogDTO> findPostureLogDTOList) {
        List<WeeklyDashBoardPostureLogDTO> weeklyDashBoardPostureLogDTOList = new ArrayList<>();
        Map<LocalDate, List<FindPostureLogDTO>> findPostureLogDTOListPerDate = new HashMap<>();

        if(!findPostureLogDTOList.isEmpty()) {
            for(FindPostureLogDTO findPostureLogDTO: findPostureLogDTOList) {
                findPostureLogDTOListPerDate.putIfAbsent(findPostureLogDTO.getDate(), new ArrayList<>());
                findPostureLogDTOListPerDate.get(findPostureLogDTO.getDate()).add(findPostureLogDTO);
            }
        }
        
        for (LocalDate date: findPostureLogDTOListPerDate.keySet()) {
            WeeklyDashBoardPostureLogDTO weeklyDashBoardPostureLogDTO = new WeeklyDashBoardPostureLogDTO(
                    date,
                    calcDailyPostureLogService.calculate(findPostureLogDTOListPerDate.get(date))
            );

            weeklyDashBoardPostureLogDTOList.add(weeklyDashBoardPostureLogDTO);
        }

        return weeklyDashBoardPostureLogDTOList;
    }
}
