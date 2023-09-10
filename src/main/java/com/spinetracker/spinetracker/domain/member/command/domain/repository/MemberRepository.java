package com.spinetracker.spinetracker.domain.member.command.domain.repository;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
