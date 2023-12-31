package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberDTO;
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

    private static Stream<Arguments> getUpdateMemberInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new UpdateMemberDTO(
                                "profileimage",
                                "효정"
                        )
                ),
                Arguments.of(
                        2L,
                        new UpdateMemberDTO(
                                "profileimage",
                                "지원"
                        )
                )
        );
    }

    @DisplayName("updateMemberDTO를 통해 사용자 정보 수정이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getUpdateMemberInfo")
    void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO) {

        Assertions.assertDoesNotThrow(
                () -> updateMemberService.updateMember(memberId, updateMemberDTO)
        );
    }
}