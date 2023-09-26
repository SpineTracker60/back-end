package com.spinetracker.spinetracker.domain.board.command.application.controller;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreateProductDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.ProductUrlResponseDTO;
import com.spinetracker.spinetracker.domain.board.command.application.service.CreateBoardService;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Product;
import com.spinetracker.spinetracker.domain.board.command.domain.service.RequestProductInfo;
import com.spinetracker.spinetracker.domain.board.command.infra.response.CoupangOpenGraphResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Product", description = "상품 관련 API")
@RestController
@RequestMapping("/product")
public class ProductController {

    private final CreateBoardService createBoardService;
    private final RequestProductInfo requestProductInfo;

    @Autowired
    public ProductController(CreateBoardService createBoardService, RequestProductInfo requestProductInfo) {
        this.createBoardService = createBoardService;
        this.requestProductInfo = requestProductInfo;
    }

    @Operation(
            summary = "상품 등록",
            description = "게시판에 글을 등록할 때 상품을 등록 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductUrlResponseDTO.class))}),
    })
    @PostMapping
    public ResponseEntity<ProductUrlResponseDTO> createProduct(@RequestBody ProductUrlResponseDTO productUrlRequestDTO) {

        CoupangOpenGraphResponse coupangOpenGraphResponse = requestProductInfo.getCoupangInfo(productUrlRequestDTO.getProductUrl());

        Product product = createBoardService.createProduct(new CreateProductDTO(
                coupangOpenGraphResponse.getProductId(),
                coupangOpenGraphResponse.getUrl(),
                coupangOpenGraphResponse.getImage(),
                coupangOpenGraphResponse.getTitle()));

        return ResponseEntity.created(URI.create("/product"))
                .body(new ProductUrlResponseDTO(
                        product.getProductName(),
                        product.getProductId(),
                        product.getProductUrl(),
                        product.getImageUrl()
                ));
    }
}
