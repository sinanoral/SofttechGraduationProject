package com.softtech.dao;

import com.softtech.model.entity.Category;
import com.softtech.model.responseDto.CategoryDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    @Query(value = "select new com.softtech.model.responseDto.CategoryDetailsDto(c.name, c.vatRate, min(p.price), max(p.price), avg(p.price), count(p)) " +
            "from Category c " +
            "inner join c.products p " +
            "group by c")
    List<CategoryDetailsDto> getCategoryDetails();
}
