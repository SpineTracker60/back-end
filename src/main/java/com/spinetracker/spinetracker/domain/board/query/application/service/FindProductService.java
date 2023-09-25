package com.spinetracker.spinetracker.domain.board.query.application.service;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindProductDTO;
import com.spinetracker.spinetracker.domain.board.query.domain.repository.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProductService {

    private final ProductMapper productMapper;

    @Autowired
    public FindProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public FindProductDTO findById(Long id) {

        return productMapper.findById(id);
    }
}
