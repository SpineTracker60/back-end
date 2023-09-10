package com.spinetracker.spinetracker.domain.posture.command.domain.repository;

import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity.PostureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostureLogRepository extends JpaRepository<PostureLog, Long> {

}
