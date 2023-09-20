package com.spinetracker.spinetracker.domain.member.query.domain.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberInfoMapper {

    Boolean isAddedInformation(Long id);
}
