package com.ra.base_spring_mvc.model.dao.user;

import com.ra.base_spring_mvc.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SessionFactory sessionFactory ;
    @Override
    public List<User> getListUser() {
       List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           users = session.createQuery("from User ",User.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            return true ;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public User findById(int id) {
        try (Session session = sessionFactory.openSession()){
           return session.createQuery("from User u where u.id = :_id",User.class)
                    .setParameter("_id",id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
