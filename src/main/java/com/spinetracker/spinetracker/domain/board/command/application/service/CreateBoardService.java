package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.BoardDTO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.ProductVO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.WriterVO;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateBoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public CreateBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board createPost(BoardDTO boardDTO, Long memberId) {
        WriterVO writerVO = new WriterVO(memberId);
        ProductVO productVO = new ProductVO(boardDTO.getProductId(), boardDTO.getProductUrl());
        Board createdPost = new Board(
                boardDTO.getTitle(),
                boardDTO.getContent(),
                writerVO,
                productVO
        );

        return boardRepository.save(createdPost);
    }
}
