package com.softtech.model.responseDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductGetDto {
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private BigDecimal vatAmount;
    private BigDecimal vatInclusivePrice;
}
