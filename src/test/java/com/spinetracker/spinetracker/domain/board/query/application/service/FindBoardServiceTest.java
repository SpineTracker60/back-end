package com.spinetracker.spinetracker.domain.board.query.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.service.CreateBoardService;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindPostDTO;
import com.spinetracker.spinetracker.domain.board.query.domain.repository.BoardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
class FindBoardServiceTest {

    @Autowired
    private FindBoardService findBoardService;

    private static Stream<Arguments> getBoardInfo() {
        return Stream.of(
                Arguments.of(
                        new FindPostDTO(
                                1L,
                                1L,
                                "게시물내용",
                                    1L
                        )
                )
        );
    }

    @DisplayName("FindBoardDTO를 통해 게시물이 조회되는지 확인")
    @ParameterizedTest
    @MethodSource("getBoardInfo")
    @Transactional
    void boardInfo(FindPostDTO findPostDTO) {

//        List<FindPostDTO> findAllPostList = new ArrayList<>();
//
//        findAllPostList.add(findPostDTO);
//        when(boardMapper.findAllPost()).thenReturn(findAllPostList);

        Assertions.assertDoesNotThrow(() -> findBoardService.findAllPost());
    }
}