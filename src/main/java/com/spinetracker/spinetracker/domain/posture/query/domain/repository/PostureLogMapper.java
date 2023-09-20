package com.spinetracker.spinetracker.domain.posture.query.domain.repository;

import com.spinetracker.spinetracker.domain.posture.query.application.dto.FindPostureLogDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostureLogMapper {

    List<FindPostureLogDTO> findDailyPosture(Map<String, Object> param);

    List<FindPostureLogDTO> findWeekly(Map<String, Object> params);
}
