package com.spinetracker.spinetracker.infra.firebase.query.domain.repository;

import com.spinetracker.spinetracker.infra.firebase.query.application.dto.FindFcmTokenDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FcmTokenMapper {

    FindFcmTokenDTO findByMemberId(Long memberId);
}
