package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.service.category.CategoryService;
import com.ra.base_spring_mvc.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ShopProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String shop(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "5") int size) {

        List<Product> products = productService.findAll(page,size);
        model.addAttribute("products", products);
        model.addAttribute("isProduct", "products");
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",productService.totalPages(size));
        return "user/product/shop-product";
    }
}
