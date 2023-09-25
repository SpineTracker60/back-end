package com.spinetracker.spinetracker.domain.posture.command.application.service;

import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.PostureBody;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity.PostureLog;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype.PostureTag;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.DateTimeVO;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.MemberVO;
import com.spinetracker.spinetracker.domain.posture.command.domain.repository.PostureLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatePostureLogService {

    private final PostureLogRepository postureLogRepository;

    @Autowired
    public CreatePostureLogService(PostureLogRepository postureLogRepository) {
        this.postureLogRepository = postureLogRepository;
    }

    @Transactional
    public PostureLog create(Long memberId, CreatePostureLogDTO createPostureLogDTO) {

        MemberVO member = new MemberVO(memberId);
        DateTimeVO dateTime = new DateTimeVO(
                createPostureLogDTO.getDate(),
                createPostureLogDTO.getStartTime(),
                createPostureLogDTO.getEndTime()
        );
        PostureTag postureTag = PostureTag.valueOf(createPostureLogDTO.getPostureTag());
        PostureLog newPostureLog = new PostureLog(member, postureTag, dateTime);
        postureLogRepository.save(newPostureLog);
        return newPostureLog;
    };
}
