package com.softtech.controller;

import com.softtech.model.requestDto.CategoryUpdateDto;
import com.softtech.model.responseDto.CategoryDetailsDto;
import com.softtech.model.responseDto.CategoryGetDto;
import com.softtech.model.responseDto.RestResponse;
import com.softtech.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(tags = "Category Controller")
    @GetMapping()
    public ResponseEntity<RestResponse<List<CategoryGetDto>>> getAllCategories() {
        return ResponseEntity.ok(RestResponse.of(categoryService.getAllCategories()));
    }

    @Operation(tags = "Category Controller")
    @GetMapping("/details")
    public ResponseEntity<RestResponse<List<CategoryDetailsDto>>> getCategoryDetails() {
        return ResponseEntity.ok(RestResponse.of(categoryService.getCategoryDetails()));
    }

    @Operation(tags = "Category Controller")
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> updateVatRateById(@PathVariable @Min(1) Long id,
                                                                @RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        categoryService.updateVatRateById(id, categoryUpdateDto);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
