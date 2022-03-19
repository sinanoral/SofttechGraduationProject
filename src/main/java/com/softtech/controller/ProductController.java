package com.softtech.controller;

import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import com.softtech.model.responseDto.ProductGetDto;
import com.softtech.model.responseDto.RestResponse;
import com.softtech.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    @Operation(tags = "Product Controller")
    @GetMapping()
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProducts() {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProducts()));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/between")
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProducts(@RequestParam BigDecimal min,
                                                                            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProducts(min, max)));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ProductGetDto>> getProductById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(RestResponse.of(productService.getProductById(id)));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<RestResponse<List<ProductGetDto>>> getAllProductsByCategoryId(@PathVariable @Min(1) Long categoryId) {
        return ResponseEntity.ok(RestResponse.of(productService.getAllProductsByCategoryId(categoryId)));
    }

    @Operation(tags = "Product Controller")
    @PostMapping()
    public ResponseEntity<RestResponse<Void>> createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        productService.createProduct(productCreateDto);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Product Controller")
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> updateProductById(@PathVariable @Min(1) Long id,
                                                                @RequestBody @Valid ProductUpdateDto productUpdateDto) {
        productService.updateProductById(id, productUpdateDto);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Product Controller")
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> updateProductPriceById(@PathVariable @Min(1) Long id,
                                                                     @RequestParam BigDecimal price) {
        productService.updateProductPriceById(id, price);
        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Product Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteProductById(@PathVariable @Min(1) Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
