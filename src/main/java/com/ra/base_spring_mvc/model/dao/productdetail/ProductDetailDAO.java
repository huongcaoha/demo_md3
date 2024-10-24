package com.ra.base_spring_mvc.model.dao.productdetail;


import com.ra.base_spring_mvc.model.entity.ProductDetail;

import java.util.List;

public interface ProductDetailDAO {


    List<ProductDetail> findAll();

    List<ProductDetail> findByProductId(int id);

    ProductDetail findById(int id);

    boolean createProduct(ProductDetail productDetail);

    boolean updateProduct(ProductDetail productDetail);

    void deleteProduct(int id);

    boolean updateProductDetail(ProductDetail productDetail);
    ProductDetail findByColorIdSizeId(int colorId,int sizeId);

}
