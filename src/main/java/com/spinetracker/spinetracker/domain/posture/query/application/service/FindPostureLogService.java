package com.spinetracker.spinetracker.domain.posture.query.application.service;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.query.domain.repository.PostureLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FindPostureLogService {

    private final PostureLogMapper postureLogMapper;

    @Autowired
    public FindPostureLogService(PostureLogMapper postureLogMapper) {
        this.postureLogMapper = postureLogMapper;
    }

    public List<FindPostureLogDTO> findByMemberId(Long memberId) {

        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("todayDate", LocalDate.now());

        return postureLogMapper.findDailyPosture(params);
    }
}
