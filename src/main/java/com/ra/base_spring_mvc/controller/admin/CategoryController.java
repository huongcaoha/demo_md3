package com.ra.base_spring_mvc.controller.admin;


import com.ra.base_spring_mvc.model.entity.Category;
import com.ra.base_spring_mvc.model.entity.dto.CategoryDto;
import com.ra.base_spring_mvc.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

@RequestMapping("/admin/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategory(Model model){
        List<Category> categories=categoryService.getListCategory();
        model.addAttribute("categories",categories);
        return "admin/category/category-management";
    }
    @GetMapping("/add")
    public String addCate(Model model,@ModelAttribute("category") CategoryDto categoryDto){
        model.addAttribute("category",categoryDto);
        return "admin/category/add";
    }
    @PostMapping("/add")
    public String createCate(@Valid  @ModelAttribute("category") CategoryDto categoryDto, BindingResult result){
        if (result.hasErrors()){
            for (ObjectError allError : result.getAllErrors()){
                System.out.println(allError.getDefaultMessage());
            }
            return "admin/category/add";
        }

        categoryService.addCategory(categoryDto);
        return "redirect:/admin/category";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Category category=categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/category/update";
    }

    @PostMapping("/edit/{id}")
    public String update( @Valid @PathVariable int id,@ModelAttribute("category") CategoryDto categoryDto){

        if (categoryService.updateCategory(categoryDto)){
            return "redirect:/admin/category";
        }
        return "admin/category/update"+id;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";

    }
}
