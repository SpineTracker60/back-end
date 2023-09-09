package com.spinetracker.spinetracker.domain.posture.command.application.service;

import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureDTO;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity.PostureLog;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype.PostureTag;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.DateTimeVO;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.MemberVO;
import com.spinetracker.spinetracker.domain.posture.command.domain.repository.PostureLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostureLogService {

    private final PostureLogRepository postureLogRepository;

    @Autowired
    public CreatePostureLogService(PostureLogRepository postureLogRepository) {
        this.postureLogRepository = postureLogRepository;
    }

    public PostureLog create(CreatePostureDTO createPostureDTO) {

        MemberVO member = new MemberVO(createPostureDTO.getMemberId());
        DateTimeVO dateTime = new DateTimeVO(
                createPostureDTO.getDate(),
                createPostureDTO.getStartTime(),
                createPostureDTO.getEndTime()
        );
        PostureTag postureTag = PostureTag.valueOf(createPostureDTO.getPostureTag());
        PostureLog newPostureLog = new PostureLog(member, postureTag, dateTime);

        return postureLogRepository.save(newPostureLog);
    };
}
