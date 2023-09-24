package com.spinetracker.spinetracker.infra.firebase.command.domain.repository;


import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.FCMToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FcmTokenRepository extends JpaRepository<FCMToken, Long> {

    Optional<FCMToken> findByMember_Id(Long memberId);

}
