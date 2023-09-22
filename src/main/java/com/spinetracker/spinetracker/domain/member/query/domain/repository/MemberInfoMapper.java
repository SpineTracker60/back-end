package com.spinetracker.spinetracker.domain.member.query.domain.repository;

import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberInfoMapper {

    Boolean isAddedInformation(Long id);

    FindMemberInfoDTO findInfoById(Long memberId);
}
