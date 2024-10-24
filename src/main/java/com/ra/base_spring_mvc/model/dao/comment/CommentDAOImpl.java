package com.ra.base_spring_mvc.model.dao.comment;

import com.ra.base_spring_mvc.model.entity.Comment;
import com.ra.base_spring_mvc.model.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CommentDAOImpl implements CommentDAO{
    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public List<Comment> getListComment() {
        List<Comment> comments = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           comments = session.createQuery("from Comment ",Comment.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments ;
    }

    @Override
    public boolean addComment(Comment comment) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateComment(Comment comment) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteComment(Comment comment) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(comment);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false ;
        }finally {
            session.close();
        }
    }

    @Override
    public Comment findById(int _id) {
     List<Comment> comments = getListComment();
     return comments.stream().filter(com -> com.getId() == _id).findFirst().orElse(null);
    }

    @Override
    public List<Comment> getListPagination(int page, int size) {
        List<Comment> comments = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            comments = session.createQuery("from Comment ",Comment.class)
                    .setFirstResult((page - 1 ) * size).
                    setMaxResults(size).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments ;
    }
}
