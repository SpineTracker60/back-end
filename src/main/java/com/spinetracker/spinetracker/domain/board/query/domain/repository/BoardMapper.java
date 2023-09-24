package com.spinetracker.spinetracker.domain.board.query.domain.repository;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindPostDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BoardMapper {

    List<FindPostDTO> findAllPost();
}
