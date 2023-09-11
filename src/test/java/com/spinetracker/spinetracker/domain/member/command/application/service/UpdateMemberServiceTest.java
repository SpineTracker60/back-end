package com.spinetracker.spinetracker.domain.member.command.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class UpdateMemberServiceTest {

    @Autowired
    private UpdateMemberService updateMemberService;

    private static Stream<Arguments> getUpdateMemberByLocalInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new UpdateMemberByLocalDTO(
                                "123123123!@#",
                                "차단된 사용자",
                                "효정",
                                "FEMALE",
                                "TWENTY",
                                "학생"
                        )
                ),
                Arguments.of(
                        2L,
                        new UpdateMemberByLocalDTO(
                                "TEST1234$$",
                                "MEMBER",
                                "지원",
                                "MALE",
                                "TEN",
                                "대학생"
                        )
                )
        );
    }

    @DisplayName("UpdateMemberByLocalDTO를 통해 사용자 정보 수정이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getUpdateMemberByLocalInfo")
    void updateMemberByLocal(Long memberId, UpdateMemberByLocalDTO updateMemberByLocalDTO) {

        Assertions.assertDoesNotThrow(
                () -> updateMemberService.updateMemberByLocal(memberId, updateMemberByLocalDTO)
        );
    }
}