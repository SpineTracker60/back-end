package com.spinetracker.spinetracker.domain.posture.query.domain.service;

import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype.PostureTag;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.DailyPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.global.common.annotation.DomainService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DomainService
public class CalcDailyPostureLogService {

    public DailyPostureLogDTO calculate(List<FindPostureLogDTO> findPostureLogDTOList) {

        int focusTime = 0;
        int unstableTime = 0;
        int CountSleep = 0;
        int CountWarn = 0;
        Map<String, Integer> postureTime = new HashMap<>();
        for(PostureTag tag : PostureTag.values()) {
            if (!tag.name().equals("START") && !tag.name().equals("END")) {
                postureTime.putIfAbsent(tag.name(), 0);
            }
        }


        LocalTime startTime = null;
        LocalTime endTime = null;
        if(!findPostureLogDTOList.isEmpty()) {
            for(FindPostureLogDTO findPostureLogDTO : findPostureLogDTOList) {
                if(findPostureLogDTO.getPostureTag().equals("START") || findPostureLogDTO.getPostureTag().equals("END")) {
                    if(findPostureLogDTO.getPostureTag().equals("START")) {
                        startTime = findPostureLogDTO.getStartTime();
                    }
                    else {
                        endTime = findPostureLogDTO.getEndTime();
                    }
                } else {
                    Duration duration = Duration.between(findPostureLogDTO.getStartTime(), findPostureLogDTO.getEndTime());
                    int diffSec = (int) duration.getSeconds();
                    unstableTime = unstableTime + diffSec;
                    postureTime.put(findPostureLogDTO.getPostureTag(), postureTime.get(findPostureLogDTO.getPostureTag()) + diffSec);
                    if (findPostureLogDTO.getPostureTag().equals("SLEEPINESS")) {
                        CountSleep = CountSleep + 1;

                    }
                }
            }
            Duration duration = Duration.between(startTime, endTime);
            focusTime = (int) duration.getSeconds();
        }
        return new DailyPostureLogDTO(focusTime, unstableTime, CountSleep, CountWarn, postureTime);
    }
}
