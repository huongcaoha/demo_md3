package com.ra.base_spring_mvc.model.dao.product;

import com.ra.base_spring_mvc.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository

public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll(int page, int size) {

        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product ", Product.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            session.close();
        }
        return products;
    }

    @Override

    public List<Product> sortProduct(int page, int size) {
        List<Product> products = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            products = session.createQuery("from Product p order by p.productName", Product.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public List<Product> searchProduct(String productName, int page, int size) {

        List<Product> products = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            if (productName == null || productName.isEmpty()) {
                productName = "%";

            } else {
                productName = "%" + productName + "%";
                products = session.createQuery("from Product p where p.productName like :_productName or p.description like :_productName", Product.class)
                        .setParameter("_productName", productName)
                        .setFirstResult(page * size)
                        .setMaxResults(size)

                        .getResultList();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findAll() {

        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product ", Product.class)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            session.close();
        }
        return products;
    }

    @Override
    public long totalElement() {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(p) from Product p", Long.class)
                    .getSingleResult();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override

    public long totalSearchElement(String productName) {

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(p) from Product p where p.productName like :_productName or p.description like :_productName", Long.class)
                    .setParameter("_productName", productName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();
        Product product = new Product();
        try {
            product = session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
        }
        return product;
    }

    @Override
    public boolean createProduct(Product product) {

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {

            session.close();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {

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

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {

            session.close();
        }
    }
}
