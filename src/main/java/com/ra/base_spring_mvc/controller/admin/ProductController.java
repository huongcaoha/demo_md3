package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Product;


import com.ra.base_spring_mvc.model.entity.dto.ProductDto;

import com.ra.base_spring_mvc.model.service.category.CategoryService;
import com.ra.base_spring_mvc.model.service.product.ProductService;
import com.ra.base_spring_mvc.model.service.product.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller

@RequestMapping("/admin/product")

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StatusService statusService;
    @GetMapping
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size) {

        List<Product> products = productService.findAll(page,size);
        model.addAttribute("products", products);

        model.addAttribute("isProduct", "products");

        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",productService.totalPages(size));
        return "admin/product/product-management";
    }
    @GetMapping("/add")

    public String add(Model model,@ModelAttribute("products") ProductDto productDto) {



        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("products", productDto);
        return "admin/product/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("products") ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                System.out.println(allError.getDefaultMessage());
            }
            return "admin/product/add";
        }
        productService.createProduct(productDto);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Product product=productService.findById(id);
        ProductDto productDto = productService.converseProduct(product);
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("image",product.getImage());
        return "admin/product/update";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute("product") ProductDto productDto) {
        Product oldProduct = productService.findById(id);
        Product product = productService.converseProductDto(productDto);
        if(product.getImage() == null){
            product.setImage(oldProduct.getImage());
        }
        if (productService.updateProduct(productDto)) {
            return "redirect:/admin/product";
        }
        return "admin/product/update" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/search")
    public String search(@RequestParam("search") String productName,Model model,@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){
        List<Product> productList= productService.searchProduct(productName,page,size);
        model.addAttribute("products",productList);
        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("page",page);
        model.addAttribute("size",size);

        model.addAttribute("isSearch",productName);
        model.addAttribute("totalPage",productService.totalSearchPages(size, productName));
       return "admin/product/product-management";
    }
    @GetMapping("/sort")
    public String sort(Model model,@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){

        List<Product> productList= productService.sortProduct(page,size);
        model.addAttribute("products",productList);
        model.addAttribute("categories", categoryService.getListCategory());
        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("isSort","productList");
        model.addAttribute("totalPage",productService.totalPages(size));
        return "admin/product/product-management";
    }

    }

