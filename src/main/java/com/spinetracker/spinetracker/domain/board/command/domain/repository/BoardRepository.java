package com.spinetracker.spinetracker.domain.board.command.domain.repository;

import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardByIdAndWriter_Id(Long id, Long memberId);
}
