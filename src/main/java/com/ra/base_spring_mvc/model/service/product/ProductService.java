package com.ra.base_spring_mvc.model.service.product;

import com.ra.base_spring_mvc.model.entity.Product;

import com.ra.base_spring_mvc.model.entity.dto.ProductDto;


import java.util.List;

public interface ProductService {
    List<Product> findAll(int page, int size);

    List<Product> findAll();

    List<Product> searchProduct(String productName,int page, int size);

    double totalPages(int size);

    Product findById(int id);


    boolean createProduct(ProductDto productDto);

    boolean updateProduct(ProductDto productDto);

    void deleteProduct(int id);

    double totalSearchPages(int size,String productName);

    List<Product> sortProduct(int page, int size);

    Product converseProductDto(ProductDto productDto);

    ProductDto converseProduct(Product product);

}
