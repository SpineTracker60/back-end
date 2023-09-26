package com.spinetracker.spinetracker.domain.board.command.application.controller;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreateProductDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.ProductUrlRequestDTO;
import com.spinetracker.spinetracker.domain.board.command.application.service.CreateBoardService;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.lib.OpenGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final CreateBoardService createBoardService;

    @Autowired
    public ProductController(CreateBoardService createBoardService) {
        this.createBoardService = createBoardService;
    }

    @PostMapping
    public ResponseEntity<ProductUrlRequestDTO> createProduct(@RequestBody ProductUrlRequestDTO productUrlRequestDTO) throws Exception {

        OpenGraph productOG = new OpenGraph(productUrlRequestDTO.getProductUrl(), true);
        createBoardService.createProduct( new CreateProductDTO(null,productOG.getContent("url"),productOG.getContent("image"),productOG.getContent("title")));

        return ResponseEntity.created(URI.create("/product"))
                .body(new ProductUrlRequestDTO(
                        productUrlRequestDTO.getProductUrl()
                ));
    }
}
