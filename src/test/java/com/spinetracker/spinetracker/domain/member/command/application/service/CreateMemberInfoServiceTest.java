package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
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
@Transactional
class CreateMemberInfoServiceTest {

    @Autowired
    private CreateMemberInfoService createMemberInfoService;

    private static Stream<Arguments> getCreateMemberInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new CreateMemberInfoDTO(
                                "FEMALE",
                                LocalDate.now(),
                                "학생"
                        )
                ),
                Arguments.of(
                        2L,
                        new CreateMemberInfoDTO(
                                "MALE",
                                LocalDate.parse("2023-09-15"),
                                "학생"
                        )
                )
        );
    }

    @DisplayName("MemberInfoDTO를 통해 사용자 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreateMemberInfo")
    void createMemberInfo(Long memberId, CreateMemberInfoDTO memberInfoDTO) {

        Assertions.assertDoesNotThrow(
                () -> createMemberInfoService.createMemberInfo(memberInfoDTO, memberId)
        );
    }

}