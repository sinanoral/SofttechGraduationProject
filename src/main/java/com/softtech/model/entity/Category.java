package com.softtech.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "CATEGORY")
public class Category extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "VAT_RATE", nullable = false, precision = 19, scale = 2)
    private BigDecimal vatRate;
}
