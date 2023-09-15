package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.MemberInfoDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UpdateMemberInfoServiceTest {

    @Autowired
    public UpdateMemberInfoService updateMemberInfoService;

    private static Stream<Arguments> getUpdateMemberInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new MemberInfoDTO(
                                "FEMALE",
                                LocalDate.parse("1995-06-04"),
                                "학생"

                        )
                ),Arguments.of(
                        2L,
                        new MemberInfoDTO(
                                "MALE",
                                LocalDate.parse("1995-06-04"),
                                "대학생"

                        )
                )
        );
    }

    @DisplayName("MemberInfoDTO를 통해 사용자 수정이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getUpdateMemberInfo")
    void updateMemberInfo(Long memberId, MemberInfoDTO memberInfoDTO) {

        Assertions.assertDoesNotThrow(
                () -> updateMemberInfoService.updateMemberInfo(memberInfoDTO, memberId)
        );
    }
}