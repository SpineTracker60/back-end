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
class DeleteBoardServiceTest {

    @Autowired
    private DeleteBoardService deleteBoardService;

    public static Stream<Arguments> getDeleteBoardInfo() {
        return Stream.of(
                Arguments.of(
                        1L,
                        2L,
                        new BoardDTO(
                                "게시물제목",
                                "게시물내용",
                                "상품번호",
                                "상품URL"
                        )
                )
        );
    }

    @DisplayName("BoardDTO 통해 게시물 삭제가 되는지 확인")
    @ParameterizedTest
    @MethodSource("getDeleteBoardInfo")
    void deletePost(Long boardId, Long memberId, BoardDTO boardDTO) {

        Assertions.assertDoesNotThrow(
                () -> deleteBoardService.deletePost(boardId, memberId, boardDTO)
        );
    }
}