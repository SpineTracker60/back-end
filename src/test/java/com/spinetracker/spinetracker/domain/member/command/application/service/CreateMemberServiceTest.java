package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberByLocalDTO;
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
class CreateMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    private static Stream<Arguments> getCreateMemberByLocalInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberByLocalDTO(
                                "email@test.com",
                                "123456!@#",
                                "효정",
                                "FEMALE",
                                "TEN",
                                "학생"
                        )
                ),
                Arguments.of(
                        new CreateMemberByLocalDTO(
                                "email2@test.com",
                                "test1234!",
                                "지원",
                                "MALE",
                                "TWENTY",
                                "학생"
                        )
                )
        );
    }

    @DisplayName("CreateMemberByLocalDTO를 통해 사용자 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreateMemberByLocalInfo")
    void createMemberByLocal(CreateMemberByLocalDTO createMemberByLocalDTO) {

        Assertions.assertDoesNotThrow(
                () -> createMemberService.createMemberByLocal(createMemberByLocalDTO)
        );
    }
}