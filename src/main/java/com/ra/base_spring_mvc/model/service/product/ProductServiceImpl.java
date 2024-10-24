package com.ra.base_spring_mvc.model.service.product;

import com.ra.base_spring_mvc.model.dao.product.ProductDAO;

import com.ra.base_spring_mvc.model.entity.Category;
import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.dto.ProductDto;

import com.ra.base_spring_mvc.model.entity.Product;

import com.ra.base_spring_mvc.model.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private FileService fileService;

    @Override
    public List<Product> findAll(int page, int size) {
        return productDAO.findAll(page,size);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public double totalSearchPages(int size,String productName) {
        return Math.ceil((double) productDAO.totalSearchElement(productName) / size);
    }

    @Override
    public List<Product> sortProduct(int page, int size) {
        return productDAO.sortProduct(page,size);
    }

    @Override

    public double totalPages(int size) {
        return Math.ceil((double) productDAO.totalElement() / size);
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> searchProduct(String productName,int page, int size) {
        return productDAO.searchProduct(productName,page,size);
    }

    @Override

    public boolean createProduct(ProductDto productDto) {

        String image = fileService.uploadImage(productDto.getFileImageProduct());
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImage(image);
        product.setCategory(productDto.getCategory());


        return productDAO.createProduct(product);
    }

    @Override

    public boolean updateProduct(ProductDto productDto) {
        String image = null;
        if(productDto.getFileImageProduct() != null && productDto.getFileImageProduct().getSize() > 0) {
            image = fileService.uploadImage(productDto.getFileImageProduct());
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImage(image);
        product.setCategory(productDto.getCategory());

        return productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
    productDAO.deleteProduct(id);
    }


    @Override
    public Product converseProductDto(ProductDto productDto) {
        String image = null;
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        if(productDto.getFileImageProduct().getSize() > 0) {
            image = fileService.uploadImage(productDto.getFileImageProduct());
        }
        product.setImage(image);
        return product ;
    }

    @Override
    public ProductDto converseProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(productDto.getCategory());
        return productDto;
    }

}
