package com.ra.base_spring_mvc.model.service.productdetail;

import com.ra.base_spring_mvc.model.entity.ProductDetail;
import com.ra.base_spring_mvc.model.entity.dto.ProductDetailDto;

import java.util.List;

public interface ProductDetailService {

    List<ProductDetail> findAll();

    List<ProductDetail> findByProductId(int id);
    ProductDetail findById(int id);

    boolean createProduct(int id,ProductDetailDto productDetailDto);

    boolean updateProduct(int id,ProductDetailDto productDetailDto);

    void deleteProduct(int id);

    boolean updateProductDetail(ProductDetail productDetail);
}
