package com.spinetracker.spinetracker.domain.member.query.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberInfoService;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberService;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.MemberInfo;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
class FindMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private FindMemberService findMemberService;

    @Autowired
    private CreateMemberInfoService createMemberInfoService;

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
                )

        );
    }

    private static Stream<Arguments> isAddedInformation() {
            return Stream.of(
                    Arguments.of(
                            new CreateMemberInfoDTO(
                                    "FEMALE",
                                    LocalDate.parse("1995-06-04"),
                                    "학생"
                            )
                    ));
        }

    @DisplayName("UID를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    @Transactional
    void findByUID(CreateMemberDTO createMemberDTO) {
        createMemberService.createMember(createMemberDTO);

        Assertions.assertNotNull(findMemberService.findByUIDAndProvider(createMemberDTO.getUID(), createMemberDTO.getPlatformEnum().name()));
    }

    @DisplayName("Id를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    @Transactional
    void findById(CreateMemberDTO createMemberDTO) {
        Member createdMember = createMemberService.createMember(createMemberDTO);

        Assertions.assertNotNull(findMemberService.findById(createdMember.getId()));
    }

    @DisplayName("회원가입 시 추가 정보 입력 여부 확인")
    @ParameterizedTest
    @MethodSource("isAddedInformation")
    @Transactional
    void isAddedInformation(CreateMemberInfoDTO memberInfoDTO) {

        Long memberId = 1L;

        MemberInfo createdMemberInfo = createMemberInfoService.createMemberInfo(memberInfoDTO, memberId);

        Assertions.assertTrue(findMemberService.isAddedInformation(createdMemberInfo.getMemberVO().getMemberId()));
    }
}