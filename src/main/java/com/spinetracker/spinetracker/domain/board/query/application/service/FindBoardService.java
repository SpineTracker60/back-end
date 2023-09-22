package com.spinetracker.spinetracker.domain.board.query.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.FindBoardDTO;
import com.spinetracker.spinetracker.domain.board.query.domain.repository.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindBoardService {

    private final BoardMapper boardMapper;

    @Autowired
    public FindBoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<FindBoardDTO> findAllPost() {

        return boardMapper.findAllPost();
    }
}
