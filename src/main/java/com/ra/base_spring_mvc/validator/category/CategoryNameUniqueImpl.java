package com.ra.base_spring_mvc.validator.category;

import com.ra.base_spring_mvc.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class CategoryNameUniqueImpl implements ConstraintValidator<CategoryNameUnique,String> {
    @Autowired
    private CategoryService categoryService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.checkCategoryNameExist(s);
    }
}
