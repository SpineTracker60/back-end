package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberInfoDTO;
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
class UpdateMemberInfoServiceTest {

    @Autowired
    public UpdateMemberInfoService updateMemberInfoService;

    private static Stream<Arguments> getUpdateMemberInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new UpdateMemberInfoDTO(
                                "FEMALE",
                                LocalDate.parse("1995-06-04"),
                                "학생",
                                1L

                        )
                ),Arguments.of(2L,
                        new UpdateMemberInfoDTO(
                                "MALE",
                                LocalDate.parse("1995-06-04"),
                                "대학생",
                                2L

                        )
                )
        );
    }

    @DisplayName("MemberInfoDTO를 통해 사용자 수정이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getUpdateMemberInfo")
    void updateMemberInfo(Long memberId, UpdateMemberInfoDTO memberInfoDTO) {

        Assertions.assertDoesNotThrow(
                () -> updateMemberInfoService.updateMemberInfo(memberInfoDTO, memberId)
        );
    }
}