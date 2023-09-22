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
class UpdateBoardServiceTest {

    @Autowired
    private UpdateBoardService updateBoardService;

    private static Stream<Arguments> getUpdateBoardInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        2L,
                        new BoardDTO(
                                "게시판제목",
                                "게시판내용",
                                "상품아이디",
                                "상품주소"

                        )
                )
        );
    }

    @DisplayName("BoardDTO 통해 게시물 수정이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getUpdateBoardInfo")
    void updatePost(Long boardId, Long memberId, BoardDTO boardDTO) {

        Assertions.assertDoesNotThrow(
                () -> updateBoardService.updatePost(boardId, memberId, boardDTO)
        );
    }
}
