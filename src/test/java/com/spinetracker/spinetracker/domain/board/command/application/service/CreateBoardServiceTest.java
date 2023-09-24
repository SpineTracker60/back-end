package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
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
class CreateBoardServiceTest {

    @Autowired
    private CreateBoardService createBoardService;
    private static Stream<Arguments> getCreatePost() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new CreatePostDTO(
                                1L,
                                "상품 이름",
                                "글 내용",
                                12121L,
                                "ASKDAKDLDASD",
                                "ASDASDASDSD"
                                )
                        )
        );

    }

    @DisplayName("boardDTO를 통해 게시글이 생성 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreatePost")
    void createPost(Long memberId, CreatePostDTO boardDTO) {

        Assertions.assertDoesNotThrow(
                () -> createBoardService.createPost(memberId, boardDTO)
        );
    }
}