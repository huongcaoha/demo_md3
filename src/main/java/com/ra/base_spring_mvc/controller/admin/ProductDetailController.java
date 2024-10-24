package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.ProductDetail;
import com.ra.base_spring_mvc.model.entity.dto.ProductDetailDto;

import com.ra.base_spring_mvc.model.entity.dto.ProductDto;

import com.ra.base_spring_mvc.model.service.product.ProductService;
import com.ra.base_spring_mvc.model.service.productdetail.ProductDetailService;
import com.ra.base_spring_mvc.model.service.productdetail.color.ColorService;
import com.ra.base_spring_mvc.model.service.productdetail.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/product-detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @GetMapping("/{id}")
    public String productDetail(@PathVariable int id, Model model){
        model.addAttribute("product",productService.findById(id));
        model.addAttribute("productDetailsDto",productDetailService.findByProductId(id));

        return "admin/productdetail/product-detail";
    }
    @GetMapping("/add/{id}")
    public String add(Model model,@PathVariable int id,@ModelAttribute("productDetailsDto") ProductDetailDto productDetailDto) {

        model.addAttribute("product",productService.findById(id));
        model.addAttribute("color", colorService.findAll());
        model.addAttribute("size", sizeService.findAll());
        model.addAttribute("productDetailsDto", productDetailDto);
        return "admin/productdetail/add";
    }

    @PostMapping("/add/{id}")
    public String save(@ModelAttribute("productDetailsDto") ProductDetailDto productDetailDto,@PathVariable int id, BindingResult result,Model model) {
        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                System.out.println(allError.getDefaultMessage());
            }
            return "admin/productdetail/add"+id;
        }
        model.addAttribute("product",productService.findById(id));
        productDetailService.createProduct(id,productDetailDto);

        return "redirect:/admin/product-detail/"+id;

    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Product product=productService.findById(id);
        model.addAttribute("products", product);

        return "admin/product/update";
    }

    @PostMapping("/edit/{id}")

    public String update(@PathVariable int id, @ModelAttribute("product") ProductDto productDto) {

        if (productService.updateProduct(productDto)) {
            return "redirect:/admin/product-detail";
        }
        return "admin/product/update" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
