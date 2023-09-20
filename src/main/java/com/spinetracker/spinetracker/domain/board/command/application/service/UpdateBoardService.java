package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.BoardDTO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateBoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public UpdateBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board updatePost(BoardDTO boardDTO) {
 return null;
    }
}
