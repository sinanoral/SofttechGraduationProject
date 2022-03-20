package com.softtech.service;

import com.softtech.dao.ProductDao;
import com.softtech.mapper.ProductMapper;
import com.softtech.model.entity.Product;
import com.softtech.model.responseDto.ProductGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDao productDao;

    @Mock
    private ProductMapper mapper;

    @Test
    void whenGetAllProductsCalled_itShouldReturnProductDtoList() {

        Product product =mock(Product.class);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ProductGetDto productGetDto =mock(ProductGetDto.class);
        List<ProductGetDto> productGetDtoList = new ArrayList<>();
        productGetDtoList.add(productGetDto);

        when(productDao.findAll()).thenReturn(productList);
        when(mapper.productListToProductGetDtoList(productList)).thenReturn(productGetDtoList);

        List<ProductGetDto> result = productService.getAllProducts();

        verify(productDao).findAll();
        verify(mapper).productListToProductGetDtoList(productList);

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllProductsCalledWithParameters_itShouldReturnProductDtoList() {

        Product product1 =mock(Product.class);
        Product product2 =mock(Product.class);
        product1.setVatInclusivePrice(BigDecimal.valueOf(40));
        product2.setVatInclusivePrice(BigDecimal.valueOf(50));

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        ProductGetDto productGetDto1 =mock(ProductGetDto.class);
        ProductGetDto productGetDto2 =mock(ProductGetDto.class);
        productGetDto1.setVatInclusivePrice(BigDecimal.valueOf(40));
        productGetDto2.setVatInclusivePrice(BigDecimal.valueOf(50));

        List<ProductGetDto> productGetDtoList = new ArrayList<>();
        productGetDtoList.add(productGetDto1);
        productGetDtoList.add(productGetDto2);

        BigDecimal min = BigDecimal.valueOf(30);
        BigDecimal max = BigDecimal.valueOf(60);
        when(productDao.getAllByVatInclusivePriceBetween(min, max))
                .thenReturn(productList);

        when(mapper.productListToProductGetDtoList(productList)).thenReturn(productGetDtoList);

        List<ProductGetDto> result = productService.getAllProducts(min, max);

        verify(productDao).getAllByVatInclusivePriceBetween(min, max);
        verify(mapper).productListToProductGetDtoList(productList);

        assertEquals(2, result.size());
    }
}