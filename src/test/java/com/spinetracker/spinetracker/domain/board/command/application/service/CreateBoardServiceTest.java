package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.CreateProductDTO;
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
class CreateBoardServiceTest {

    @Autowired
    private CreateBoardService createBoardService;
    private static Stream<Arguments> getCreatePost() {
        return Stream.of(
                Arguments.of(
                        1L,
                        new CreatePostDTO(
                                1L,
                                "글 내용",
                                1L
                                )
                        )
        );

    }

//    private static Stream<Arguments> getCreateProduct() {
//        return Stream.of(
//                Arguments.of(
//                        new CreateProductDTO(
//                                1L,
//                                "상품 url",
//                                "이미지 url",
//                                "상품 이름"
//                        )
//                )
//        );
//
//    }

    @DisplayName("boardDTO를 통해 게시글이 생성 되는지 확인")
    @ParameterizedTest
    @MethodSource("getCreatePost")
    @Transactional
    void createPost(Long memberId, CreatePostDTO boardDTO) {

        Assertions.assertDoesNotThrow(
                () -> createBoardService.createPost(memberId, boardDTO)
        );
    }

//    @DisplayName("CreateProductDTO를 통해 상품이 생성 되는지 확인")
//    @ParameterizedTest
//    @MethodSource("getCreateProduct")
//    @Transactional
//    void createProduct(CreateProductDTO createProductDTO) {
//
//        Assertions.assertDoesNotThrow(
//                () -> createBoardService.createProduct(createProductDTO)
//        );
//    }
}