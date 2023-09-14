package com.spinetracker.spinetracker.domain.member.query.application.service;

import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.domain.repository.MemberMapper;
import com.spinetracker.spinetracker.global.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindMemberService {

    private final MemberMapper memberMapper;
    @Autowired
    public FindMemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public FindMemberDTO findByUID(String uid) {
        FindMemberDTO findMember = memberMapper.findByUID(uid);
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

        FindMemberDTO findMember = memberMapper.findById(memberId);

        //ExceptionAssert.isUserExist(findMember);

        return findMember;
    }

    public FindMemberDTO  findByEmail(String email) {

        FindMemberDTO findMember = memberMapper.findByEmail(email);
        //ExceptionAssert.isUserExist(findMember);

        return findMember;
    }
}
