package com.softtech.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @DecimalMin("0.05")
    @Column(name = "PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @PositiveOrZero
    @Column(name = "VAT_AMOUNT", nullable = false, precision = 19, scale = 2)
    private BigDecimal vatAmount;

    @Positive
    @Column(name = "VAT_INCLUSIVE_PRICE", nullable = false, precision = 19, scale = 2)
    private BigDecimal vatInclusivePrice;
}
