package com.spinetracker.spinetracker.domain.posture.query.domain.service;

import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype.PostureTag;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.application.dto.UnStableRatioDTO;
import com.spinetracker.spinetracker.global.common.annotation.DomainService;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DomainService
public class CalcUnStableRatioPostureLogService {

    public UnStableRatioDTO calculate(List<FindPostureLogDTO> findPostureLogDTOList) {
        Map<String, Double> unStableTotalTimeMap = new HashMap<>();
        Double unStableTotalTime = 0.0;
        for(PostureTag tag : PostureTag.values()) {
            if (!tag.name().equals("START") && !tag.name().equals("END")) {
                unStableTotalTimeMap.putIfAbsent(tag.name(), 0.0);
            }
        }

        if(!findPostureLogDTOList.isEmpty()) {
            for(FindPostureLogDTO findPostureLogDTO: findPostureLogDTOList) {
                if(!findPostureLogDTO.getPostureTag().equals("START") && !findPostureLogDTO.getPostureTag().equals("END")) {
                    Duration duration = Duration.between(findPostureLogDTO.getStartTime(), findPostureLogDTO.getEndTime());
                    int diffSec = (int) duration.getSeconds();
                    unStableTotalTime = unStableTotalTime + diffSec;
                    unStableTotalTimeMap.put(findPostureLogDTO.getPostureTag(), unStableTotalTimeMap.get(findPostureLogDTO.getPostureTag()) + diffSec);
                }
            }

            return new UnStableRatioDTO(
                    roundRatio(unStableTotalTimeMap.get("TEXTNECK") / unStableTotalTime),
                    roundRatio(unStableTotalTimeMap.get("ASYMMETRY") / unStableTotalTime),
                    roundRatio(unStableTotalTimeMap.get("STOOPED") / unStableTotalTime),
                    roundRatio(unStableTotalTimeMap.get("SLEEPINESS") / unStableTotalTime)
            );
        }
        return null;
    }

    private Double roundRatio(Double ratio) {
        return (double) Math.round(ratio*100) / 100;
    }
}
