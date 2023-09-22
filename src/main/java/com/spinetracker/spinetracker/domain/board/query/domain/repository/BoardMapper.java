package com.spinetracker.spinetracker.domain.board.query.domain.repository;

import com.spinetracker.spinetracker.domain.board.command.application.dto.FindBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {

    List<FindBoardDTO> findAllPost();
}
