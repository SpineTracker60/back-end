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

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatePostureLogService {

    private final PostureLogRepository postureLogRepository;

    @Autowired
    public CreatePostureLogService(PostureLogRepository postureLogRepository) {
        this.postureLogRepository = postureLogRepository;
    }

    public List<PostureLog> create(Long memberId, List<CreatePostureLogDTO> createPostureLogDTOList) {

        List<PostureLog> postureLogList = new ArrayList<>();
        for (CreatePostureLogDTO createPostureDto : createPostureLogDTOList) {
            MemberVO member = new MemberVO(memberId);
            DateTimeVO dateTime = new DateTimeVO(
                    createPostureDto.getDate(),
                    createPostureDto.getStartTime(),
                    createPostureDto.getEndTime()
            );
            PostureTag postureTag = PostureTag.valueOf(createPostureDto.getPostureTag());
            PostureLog newPostureLog = new PostureLog(member, postureTag, dateTime);
            postureLogRepository.save(newPostureLog);
            postureLogList.add(newPostureLog);
        }
        return postureLogList;
    };
}
