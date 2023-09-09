package com.spinetracker.spinetracker.domain.posture.command.application.service;

import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class CreatePostureLogServiceTest {

    @Autowired
    private CreatePostureLogService createPostureLogService;

    private static Stream<Arguments> getPostureInfo() {
        return Stream.of(
                Arguments.of(
                 new CreatePostureDTO(
                        1L,
                        "TEXTNECK",
                        LocalDate.now(),
                        LocalTime.now(),
                         LocalTime.now().plusMinutes(10)
                )
            ),
                Arguments.of(
                        new CreatePostureDTO(
                                1L,
                                "ASYMMETRY",
                                LocalDate.now(),
                                LocalTime.now().plusMinutes(100),
                                LocalTime.now().plusMinutes(110)
                        )
                )
        );
    }

    @DisplayName("자세 로그 생성 DTO를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getPostureInfo")
    void create(CreatePostureDTO createPostureDTO) {
        Assertions.assertDoesNotThrow(
                () -> createPostureLogService.create(createPostureDTO)
        );
    }
}