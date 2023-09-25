package com.spinetracker.spinetracker.domain.board.query.application.controller;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindProductDTO;
import com.spinetracker.spinetracker.domain.board.query.application.service.FindProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "상품 조회 관련 API")
@RestController
@RequestMapping("/product")
public class FindProductController {

    private final FindProductService findProductService;

    @Autowired
    public FindProductController(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    @Operation(
            summary = "상품 조회",
            description = "상품 ID로 상품의 정보를 조회 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindProductDTO.class))}),
    })
    @GetMapping("/{productId}")
    public ResponseEntity<FindProductDTO> getProductInfo(@PathVariable Long productId) {

        FindProductDTO findProduct = findProductService.findById(productId);

        return ResponseEntity.ok().body(new FindProductDTO(
                findProduct.getProductId(),
                findProduct.getProductUrl(),
                findProduct.getImageUrl(),
                findProduct.getProductName()
        ));
    }
}
