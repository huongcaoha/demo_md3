package com.ra.base_spring_mvc.model.dao.category;

import com.ra.base_spring_mvc.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getListCategory();
    boolean addCategory(Category category);
    boolean updateCategory(Category category);

    void deleteCategory(int id);

    Category findById(int id);
}
