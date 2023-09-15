package com.spinetracker.spinetracker.domain.member.command.domain.repository;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
}
