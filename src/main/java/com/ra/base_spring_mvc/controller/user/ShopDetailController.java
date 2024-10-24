package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.ProductDetail;
import com.ra.base_spring_mvc.model.service.product.ProductService;
import com.ra.base_spring_mvc.model.service.productdetail.ProductDetailService;
import com.ra.base_spring_mvc.model.service.productdetail.color.ColorService;
import com.ra.base_spring_mvc.model.service.productdetail.size.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product-detail")
public class ShopDetailController {
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

        List<ProductDetail> list = productDetailService.findByProductId(id);

//        model.addAttribute("productDetailsDto",);

        model.addAttribute("colors",list.stream().map(ProductDetail::getColor).collect(Collectors.toSet()));
        model.addAttribute("sizes",list.stream().map(ProductDetail::getSize).collect(Collectors.toSet()));

//        model.addAttribute("color", colorService.findById(id));
//        model.addAttribute("size", sizeService.findById(id));
        return "user/product/shop-detail";
    }
}
