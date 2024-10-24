package com.ra.base_spring_mvc.model.service.category;

import com.ra.base_spring_mvc.model.entity.Category;

import com.ra.base_spring_mvc.model.entity.dto.CategoryDto;


import java.util.List;

public interface CategoryService {
    List<Category> getListCategory();

    boolean addCategory(CategoryDto categoryDto);
    boolean updateCategory(CategoryDto categoryDto);
    void deleteCategory(int id);

    Category findById(int id);
    boolean checkCategoryNameExist(String categoryName);
}
