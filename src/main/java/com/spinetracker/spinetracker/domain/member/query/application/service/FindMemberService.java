package com.spinetracker.spinetracker.domain.member.query.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.domain.repository.MemberInfoMapper;
import com.spinetracker.spinetracker.domain.member.query.domain.repository.MemberMapper;
import com.spinetracker.spinetracker.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FindMemberService {

    private final MemberMapper memberMapper;
    private final MemberInfoMapper memberInfoMapper;
    @Autowired
    public FindMemberService(MemberMapper memberMapper, MemberInfoMapper memberInfoMapper) {
        this.memberMapper = memberMapper;
        this.memberInfoMapper = memberInfoMapper;
    }

    public FindMemberDTO findByUIDAndProvider(String uid, String provider) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("platform", provider);
        FindMemberDTO findMember = memberMapper.findByUIDAndProvider(params);
        if(findMember == null) {
            return null;
        } else {
            return findMember;
        }
    }

    public FindMemberDTO findByAccessToken(String accessToken) {
        FindMemberDTO findMember = memberMapper.findByAccessToken(accessToken);
        //ExceptionAssert.isUserExist(findMember);
        return findMember;
    }

    public FindMemberDTO findById(long memberId) throws UserNotFoundException {

        //ExceptionAssert.isUserExist(findMember);

        return memberMapper.findById(memberId);
    }


    public FindMemberDTO findByEmail(String email) {

        FindMemberDTO findMember = memberMapper.findByEmail(email);
        //ExceptionAssert.isUserExist(findMember);

        return findMember;
    }

    public Boolean isAddedInformation(Long id) {

        return !memberInfoMapper.isAddedInformation(id);
    }

    public FindMemberInfoDTO findInfoById(Long memberId) throws UserNotFoundException {

        return memberInfoMapper.findInfoById(memberId);
    }
}
