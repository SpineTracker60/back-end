package com.spinetracker.spinetracker.domain.member.query.domain.repository;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> findAll();

    FindMemberDTO findById(long id);

    FindMemberDTO findByUID(String uid);

    FindMemberDTO findByAccessToken(String accessToken);

    FindMemberDTO findByEmail(String email);
}
