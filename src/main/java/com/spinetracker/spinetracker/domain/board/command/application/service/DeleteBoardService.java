package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.BoardDTO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeleteBoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public DeleteBoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Board deletePost(Long boardId, Long memberId, BoardDTO boardDTO) {
        Optional<Board> findBoard = boardRepository.findBoardByIdAndWriter_Id(boardId, memberId);
        if(findBoard.isPresent()) {
            Board board = findBoard.get();
            board.setBoardIsDeleted(true);

            return board;
        }
        return null;
    }
}
