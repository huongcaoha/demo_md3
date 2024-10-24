package com.ra.base_spring_mvc.model.dao.productdetail;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.ProductDetail;

import com.ra.base_spring_mvc.model.service.product.ProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class ProductDetailDAOImpl implements ProductDetailDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ProductService productService;
    @Override
    public List<ProductDetail> findAll() {
        Session session= sessionFactory.openSession();
        List<ProductDetail> productDetails=new ArrayList<>();
        try {
            productDetails=session.createQuery("from ProductDetail ", ProductDetail.class)
                    .list();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            session.close();
        }
        return productDetails;
    }


    @Override
    public List<ProductDetail> findByProductId(int id) {
        Session session= sessionFactory.openSession();

        List<ProductDetail> productDetailList=new ArrayList<>();
        try {
            productDetailList=session.createQuery("select pd from ProductDetail pd where pd.product.id = :_productId", ProductDetail.class)
                    .setParameter("_productId",id).list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return productDetailList;
    }

    @Override

    public ProductDetail findByColorIdSizeId(int colorId, int sizeId) {
        Session session= sessionFactory.openSession();
        ProductDetail productDetail=new ProductDetail();
        try {
            productDetail= (ProductDetail) session.createQuery("from ProductDetail pd join Color c on pd.color.id==c.id join Size sz on pd.size.id==sz.id where pd.color.id==:_colorId and pd.size.id==:_sizeId")
                    .setParameter("_colorId",colorId)
                    .setParameter("_sizeId",sizeId)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return productDetail;
    }

    @Override

    public ProductDetail findById(int id) {
        Session session= sessionFactory.openSession();
        ProductDetail productDetail=new ProductDetail();
        try {
            productDetail=session.get(ProductDetail.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return productDetail;
    }

    @Override
    public boolean createProduct(ProductDetail productDetail) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(productDetail);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();

        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateProduct(ProductDetail productDetail) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(productDetail);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();

        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public void deleteProduct(int id) {
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();

        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateProductDetail(ProductDetail productDetail) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(productDetail);
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }


}
