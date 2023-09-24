package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.UpdatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateBoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public UpdateBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board updatePost(Long memberId, Long boardId, UpdatePostDTO updatePostDTO) {

        Optional<Board> findBoard = boardRepository.findBoardByIdAndWriter_Id(boardId, memberId);
        if(findBoard.isPresent()) {
            Board board = findBoard.get();
            board.setContent(updatePostDTO.getContent());

            boardRepository.save(board);
            return board;
        }
        return null;
    }
}
