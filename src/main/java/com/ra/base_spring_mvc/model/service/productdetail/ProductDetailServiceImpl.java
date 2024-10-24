package com.ra.base_spring_mvc.model.service.productdetail;

import com.ra.base_spring_mvc.model.dao.productdetail.ProductDetailDAO;
import com.ra.base_spring_mvc.model.entity.Category;
import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.ProductDetail;
import com.ra.base_spring_mvc.model.entity.dto.ProductDetailDto;
import com.ra.base_spring_mvc.model.service.file.FileService;
import com.ra.base_spring_mvc.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
    @Autowired
    private ProductDetailDAO productDetailDAO;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Override
    public List<ProductDetail> findAll() {
        return productDetailDAO.findAll();
    }

    @Override
    public List<ProductDetail> findByProductId(int id) {
        return productDetailDAO.findByProductId(id);
    }

    @Override
    public ProductDetail findById(int id) {
        return productDetailDAO.findById(id);
    }

    @Override
    public boolean createProduct(int id,ProductDetailDto productDetailDto) {
        Product product=productService.findById(id);
        String image = fileService.uploadImage(productDetailDto.getFileImage());
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setSize(productDetailDto.getSize());
        productDetail.setImage(image);
        productDetail.setColor(productDetailDto.getColor());
        productDetail.setStock_quantity(productDetailDto.getStock_quantity());
        productDetail.setUnit_price(productDetailDto.getUnit_price());
        return productDetailDAO.createProduct(productDetail);
    }

    @Override
    public boolean updateProduct(int id,ProductDetailDto productDetailDto) {
        Product product=productService.findById(id);
        String image = null;
        if(productDetailDto.getFileImage() != null && productDetailDto.getFileImage().getSize() > 0) {
            image = fileService.uploadImage(productDetailDto.getFileImage());
        }
        ProductDetail productDetail = new ProductDetail();
        productDetail.setId(productDetailDto.getId());
        productDetail.setProduct(product);
        productDetail.setSize(productDetailDto.getSize());
        productDetail.setImage(image);
        productDetail.setColor(productDetailDto.getColor());
        productDetail.setStock_quantity(productDetailDto.getStock_quantity());
        productDetail.setUnit_price(productDetailDto.getUnit_price());
        return productDetailDAO.createProduct(productDetail);
    }

    @Override
    public void deleteProduct(int id) {
    productDetailDAO.deleteProduct(id);
    }

    @Override
    public boolean updateProductDetail(ProductDetail productDetail) {
        return productDetailDAO.updateProductDetail(productDetail);
    }
}
