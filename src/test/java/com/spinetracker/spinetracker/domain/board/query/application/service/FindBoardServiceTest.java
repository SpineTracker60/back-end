package com.spinetracker.spinetracker.domain.board.query.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.FindBoardDTO;
import com.spinetracker.spinetracker.domain.board.query.domain.repository.BoardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FindBoardServiceTest {

    @Autowired
    private FindBoardService findBoardService;
    private static Stream<Arguments> getBoardInfo() {
        return Stream.of(
                Arguments.of(
                        new FindBoardDTO(
                                "게시물제목",
                                "게시물내용",
                                "상품번호",
                                "상품url"
                        )
                )
        );
    }

    @DisplayName("FindBoardDTO를 통해 게시물이 조회되는지 확인")
    @ParameterizedTest
    @MethodSource("getBoardInfo")
    @Transactional
    void boardInfo() {

        Assertions.assertDoesNotThrow(() -> findBoardService.findAllPost());
    }
}