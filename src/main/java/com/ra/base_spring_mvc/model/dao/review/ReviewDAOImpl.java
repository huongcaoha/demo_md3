package com.ra.base_spring_mvc.model.dao.review;

import com.ra.base_spring_mvc.model.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ReviewDAOImpl implements ReviewDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Review> getListReview() {
       List<Review> reviews = new ArrayList<>();
       try (Session session = sessionFactory.openSession()){
          reviews = session.createQuery("from Review ",Review.class).list();
       }catch (Exception e){
           e.printStackTrace();
       }
       return reviews;
    }

    @Override
    public List<Review> getListPagination(int page, int size) {
        List<Review> reviews = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            reviews = session.createQuery("from Review ",Review.class).setFirstResult((page - 1) * size)
                    .setMaxResults(size).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public boolean addReview(Review review) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(review);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateReview(Review review) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(review);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteReview(Review review) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(review);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Review findById(int _id) {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from Review r where r.id = :_id",Review.class).setParameter("_id",_id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
