package com.spinetracker.spinetracker.domain.member.command.infra.service;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.service.RequestUnlinkMember;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Autowired;

@InfraService
public class RequestUnlinkMemberImpl implements RequestUnlinkMember {

    private final UnlinkKakao unlinkKakao;
//    private final UnlinkGoogle unlinkGoogle;

    @Autowired
    public RequestUnlinkMemberImpl(UnlinkKakao unlinkKakao) {
        this.unlinkKakao = unlinkKakao;
//        this.unlinkGoogle = unlinkGoogle;
    }

    @Override
    public boolean unlinkSocial(Member member) {
        if (member.getPlatform().name().equals("KAKAO")) {
            return unlinkKakao.unlink(member.getUID());
//        } else if (member.getPlatform().name().equals("GOOGLE")) {
//            return unlinkGoogle.unlink(member.getUID());
        }


        return false;
    }
}
