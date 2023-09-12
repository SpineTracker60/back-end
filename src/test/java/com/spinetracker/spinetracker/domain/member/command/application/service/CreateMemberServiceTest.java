package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



import java.util.stream.Stream;

import static com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum.GOOGLE;

@SpringBootTest
@Transactional
class CreateMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    private static Stream<Arguments> getCreateMemberInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "email@test.com",
                                "123456!@#",
                                "profieimage",
                                GOOGLE,
                                "효정"
                        )
                ),
                Arguments.of(
                        new CreateMemberDTO(
                                "email2@test.com",
                                "test1234!",
                                "profieimage",
                                GOOGLE,
                                "지원"
                        )
                )
        );
    }

    @DisplayName("createMemberDTO를 통해 사용자 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreateMemberInfo")
    void createMember(CreateMemberDTO createMemberDTO) {

        Assertions.assertDoesNotThrow(
                () -> createMemberService.createMember(createMemberDTO)
        );
    }
}