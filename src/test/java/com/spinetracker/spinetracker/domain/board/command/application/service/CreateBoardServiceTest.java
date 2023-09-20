package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.BoardDTO;
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
                        new BoardDTO(
                                "글 제목",
                                "글 내용",
                                "효정",
                                "상품"
                                )
                        )
        );

    }

    @DisplayName("boardDTO를 통해 게시글이 생성 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreatePost")
    void createPost(Long memberId, BoardDTO boardDTO) {

        Assertions.assertDoesNotThrow(
                () -> createBoardService.createPost(boardDTO, memberId)
        );
    }
}