package com.softtech.service;

import com.softtech.dao.ProductDao;
import com.softtech.enums.ProductErrorMessage;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.ProductMapper;
import com.softtech.model.entity.Product;
import com.softtech.model.requestDto.ProductCreateDto;
import com.softtech.model.requestDto.ProductUpdateDto;
import com.softtech.model.responseDto.ProductGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductMapper mapper;

    private final CategoryService categoryService;

    public List<ProductGetDto> getAllProducts() {
        List<Product> products = productDao.findAll();
        return mapper.productListToProductGetDtoList(products);
    }

    public List<ProductGetDto> getAllProducts(BigDecimal min, BigDecimal max) {
        List<Product> products = productDao.getAllByVatInclusivePriceBetween(min, max);
        return mapper.productListToProductGetDtoList(products);
    }

    public ProductGetDto getProductById(Long id) {
        Product product = getByIdWithControl(id);
        return mapper.productToProductGetDto(product);
    }

    public List<ProductGetDto> getAllProductsByCategoryId(Long id) {
        List<Product> products = productDao.getAllByCategory_Id(id);
        return mapper.productListToProductGetDtoList(products);
    }

    public void createProduct(ProductCreateDto productCreateDto) {
        Product product = mapper.productCreateDtoProduct(productCreateDto);
        setVacInclusivePriceAndVatAmount(productCreateDto.getCategoryId(), product);
        productDao.save(product);
    }

    public void updateProductById(Long id, ProductUpdateDto productUpdateDto) {
        getByIdWithControl(id);
        Product product = mapper.productUpdateDtoToProduct(productUpdateDto);
        setVacInclusivePriceAndVatAmount(productUpdateDto.getCategoryId(), product);
        productDao.save(product);
    }

    public void updateProductPriceById(Long id, BigDecimal price) {
        Product product = getByIdWithControl(id);
        product.setPrice(price);
        setVacInclusivePriceAndVatAmount(product.getCategory().getId(), product);
        productDao.save(product);
    }

    public void deleteProductById(Long id) {
        Product product = getByIdWithControl(id);
        productDao.delete(product);
    }

    @Transactional
    public void updateProductsPriceWithNewVatRate(BigDecimal vatRate, Long categoryId) {

        List<Product> products = productDao.findAll()
                .stream()
                .filter(product -> Objects.equals(product.getCategory().getId(), categoryId))
                .peek(product -> {
                    product.setVatAmount(vatRate.multiply(product.getPrice()));
                    product.setVatInclusivePrice(product.getPrice().add(product.getVatAmount()));
                })
                .collect(Collectors.toList());

        productDao.saveAll(products);
    }

    private Product getByIdWithControl(Long id) {
        return productDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(ProductErrorMessage.PRODUCT_NOT_FOUND_ID));
    }

    private void setVacInclusivePriceAndVatAmount(Long categoryId, Product product) {
        BigDecimal vatRate = categoryService.getCategoryById(categoryId).getVatRate();
        BigDecimal taxFreePrice = product.getPrice();

        product.setVatInclusivePrice(taxFreePrice.multiply(vatRate).add(taxFreePrice));
        product.setVatAmount(product.getVatInclusivePrice().subtract(taxFreePrice));
    }
}
