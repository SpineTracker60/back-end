package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Product;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.ProductVO;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo.WriterVO;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.BoardRepository;
import com.spinetracker.spinetracker.domain.board.command.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateBoardService {

    private final BoardRepository boardRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CreateBoardService(BoardRepository boardRepository, ProductRepository productRepository) {
        this.boardRepository = boardRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Board createPost(Long memberId,CreatePostDTO createPostDTO) {
        WriterVO writerVO = new WriterVO(memberId);
        Optional<Product> product = productRepository.findById(createPostDTO.getProductId());
        if(product.isEmpty()) {
            Product createProduct = new Product(
                    createPostDTO.getProductId(),
                    createPostDTO.getProductUrl(),
                    createPostDTO.getImageUrl(),
                    createPostDTO.getProductName()
            );
            productRepository.save(createProduct);
        }
        ProductVO productVO = new ProductVO(createPostDTO.getProductId());
        Board createdPost = new Board(
                createPostDTO.getContent(),
                writerVO,
                productVO
        );
        return boardRepository.save(createdPost);
    }
}
