package com.ra.base_spring_mvc.model.dao.category;

import com.ra.base_spring_mvc.model.entity.Category;
import com.ra.base_spring_mvc.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Category> getListCategory() {
        List<Category> categories = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            categories = session.createQuery("from Category ",Category.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean addCategory(Category category) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(category);
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
    public boolean updateCategory(Category category) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(category);
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

    public void deleteCategory(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();


        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    @Override
    public Category findById(int id) {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from Category c where c.id = :_id",Category.class)
                    .setParameter("_id",id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
