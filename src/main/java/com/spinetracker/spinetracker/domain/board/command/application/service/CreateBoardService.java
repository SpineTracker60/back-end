package com.spinetracker.spinetracker.domain.board.command.application.service;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.CreateProductDTO;
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

        ProductVO productVO = new ProductVO(createPostDTO.getProductId());
        Board createdPost = new Board(
                createPostDTO.getContent(),
                writerVO,
                productVO
        );
        return boardRepository.save(createdPost);
    }

    @Transactional
    public Product createProduct(CreateProductDTO createProductDTO) {
        Optional<Product> product = productRepository.findById(createProductDTO.getProductId());
            if(product.isEmpty()) {
                Product createProduct = new Product(
                        createProductDTO.getProductId(),
                        createProductDTO.getProductUrl(),
                        createProductDTO.getImageUrl(),
                        createProductDTO.getProductName()
                );
                return productRepository.save(createProduct);
            }
            return product.get();
        }
}
