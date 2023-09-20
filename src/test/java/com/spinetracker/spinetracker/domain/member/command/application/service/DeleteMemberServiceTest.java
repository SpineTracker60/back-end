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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DeleteMemberServiceTest {

    @Autowired
    private DeleteMemberService deleteMemberService;

    private static Stream<Arguments> getDeleteMemberInfo() {
        return Stream.of(
                Arguments.of(
                    2L
                )
        );
    }

    @DisplayName("member가 회원탈퇴 되는지 확인")
    @ParameterizedTest
    @MethodSource("getDeleteMemberInfo")
    void deleteMember(Long memberId) {

//        Assertions.assertDoesNotThrow(
//                () -> deleteMemberService.delete(memberId)
//        );
    }
}