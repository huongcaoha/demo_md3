package com.ra.base_spring_mvc.model.dao.shoppingCart;

import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDao{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<ShoppingCart> getListShoppingCart(int id_user) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           shoppingCarts = session.createQuery("from ShoppingCart", ShoppingCart.class)
//                   .setParameter("_id_user",id_user)
                  .list();
          return shoppingCarts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return shoppingCarts;
    }

    @Override
    public boolean addTocart(ShoppingCart shoppingCart) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(shoppingCart);
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

    @Override
    public boolean updateTocart(ShoppingCart shoppingCart) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(shoppingCart);
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

    @Override
    public boolean deleteTocart(ShoppingCart shoppingCart) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(shoppingCart);
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

    @Override
    public ShoppingCart findById(int id) {
        try ( Session session = sessionFactory.openSession()){
           session.createQuery("from ShoppingCart s where s.id = :_id", ShoppingCart.class)
                   .setParameter("_id",id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public boolean deleteAll(int id_user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM ShoppingCart s WHERE s.user.id = :_userId")
                    .setParameter("_userId",id_user).executeUpdate();
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }


}
