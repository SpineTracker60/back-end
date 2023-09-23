package com.spinetracker.spinetracker.domain.board.query.domain.repository;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    FindProductDTO findByProductId(Long id);
}
