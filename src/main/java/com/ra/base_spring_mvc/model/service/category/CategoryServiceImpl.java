package com.ra.base_spring_mvc.model.service.category;

import com.ra.base_spring_mvc.model.dao.category.CategoryDAO;
import com.ra.base_spring_mvc.model.entity.Category;

import com.ra.base_spring_mvc.model.entity.dto.CategoryDto;
import com.ra.base_spring_mvc.model.service.file.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private FileService fileService;


    @Override
    public List<Category> getListCategory() {
        return categoryDAO.getListCategory();
    }

    @Override

    public boolean addCategory(CategoryDto categoryDto) {
        String image = fileService.uploadImage(categoryDto.getFileImageCategory());
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(image);
        category.setStatus(categoryDto.isStatus());

        return categoryDAO.addCategory(category);
    }

    @Override

    public boolean updateCategory(CategoryDto categoryDto) {
        String image = null;
        if(categoryDto.getFileImageCategory() != null && categoryDto.getFileImageCategory().getSize() > 0) {
            image = fileService.uploadImage(categoryDto.getFileImageCategory());
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        category.setImage(image);
        category.setStatus(categoryDto.isStatus());

        return categoryDAO.updateCategory(category);
    }

    @Override

    public void deleteCategory(int id) {

        categoryDAO.deleteCategory(id);

    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public boolean checkCategoryNameExist(String categoryName) {
        List<Category> categories = getListCategory();
        return categories.stream().map(Category::getCategoryName).anyMatch(cate -> cate.equals(categoryName));
    }
}
