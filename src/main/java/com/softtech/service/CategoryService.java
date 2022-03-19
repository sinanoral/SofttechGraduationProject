package com.softtech.service;

import com.softtech.dao.CategoryDao;
import com.softtech.enums.CategoryErrorMessage;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.CategoryMapper;
import com.softtech.model.entity.Category;
import com.softtech.model.requestDto.CategoryUpdateDto;
import com.softtech.model.responseDto.CategoryDetailsDto;
import com.softtech.model.responseDto.CategoryGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper mapper;
    private ProductService productService;

    @Autowired
    public void setProductService(@Lazy ProductService productService) {
        this.productService = productService;
    }

    public List<CategoryDetailsDto> getCategoryDetails() {
        return categoryDao.getCategoryDetails();
    }

    public List<CategoryGetDto> getAllCategories() {
        List<Category> categories = categoryDao.findAll();
        return mapper.categoryListToCatergoryGetDtoList(categories);
    }

    @Transactional
    public void updateVatRateById(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = getCategoryById(id);
        category.setVatRate(categoryUpdateDto.getVatRate());
        categoryDao.save(category);
        productService.updateProductsPriceWithNewVatRate(category.getVatRate(), id);
    }

    public Category getCategoryById(Long id) {
        return categoryDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(CategoryErrorMessage.CATEGORY_NOT_FOUND_ID));
    }
}
