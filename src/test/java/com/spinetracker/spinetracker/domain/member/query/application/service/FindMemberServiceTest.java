package com.spinetracker.spinetracker.domain.member.query.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberService;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FindMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private FindMemberService findMemberService;

    private static Stream<Arguments> getMemberInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "email@test.com",
                                "1122aa",
                                "profileImage",
                                PlatformEnum.GOOGLE,
                                "효정"
                        )
                ),
                Arguments.of(
                        new CreateMemberDTO(
                                "email2@test.com",
                                "123456@@",
                                "profileImage",
                                PlatformEnum.KAKAO,
                                "효정"
                        )
                )
        );
    }

    @DisplayName("UID를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void findByUID(CreateMemberDTO createMemberDTO) {
        createMemberService.createMember(createMemberDTO);

        Assertions.assertNotNull(findMemberService.findByUIDAndProvider(createMemberDTO.getUID(), createMemberDTO.getPlatformEnum().name()));
    }

    @DisplayName("Id를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void findById(CreateMemberDTO createMemberDTO) {
        Member createdMember = createMemberService.createMember(createMemberDTO);

        Assertions.assertNotNull(findMemberService.findById(createdMember.getId()));
    }
}