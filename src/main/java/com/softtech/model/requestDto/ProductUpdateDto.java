package com.softtech.model.requestDto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    @NotBlank(message = "Name can not be blank!")
    @NotNull(message = "Name can not be null!")
    private String name;

    @Min(value = 1, message = "Category id can not be negative or zero!")
    private Long categoryId;

    @DecimalMin(value = "0.0001", message = "Price can not be negative or zero!")
    private BigDecimal price;
}
