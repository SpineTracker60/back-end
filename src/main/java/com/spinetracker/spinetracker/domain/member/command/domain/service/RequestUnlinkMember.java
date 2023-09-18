package com.spinetracker.spinetracker.domain.member.command.domain.service;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;

public interface RequestUnlinkMember {

    boolean unlinkSocial(Member member);
}
