package com.ra.base_spring_mvc.model.dao.notification;

import com.ra.base_spring_mvc.model.entity.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class NotificationDAOImpl implements NotificationDAO{
    @Autowired
    private final SessionFactory sessionFactory ;

    public NotificationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Notification> getNotificationByUser(int user_id) {
        List<Notification> notifications = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            notifications = session.createQuery("from Notification n where n.user.id = :_id order by n.created_at desc ", Notification.class)
                    .setParameter("_id",user_id).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public boolean addNotification(Notification notification) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(notification);
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
    public boolean updateNotification(Notification notification) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(notification);
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
    public boolean deleteNotification(Notification notification) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(notification);
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
    public Notification findById(int id) {
        try (Session session = sessionFactory.openSession()){
          return session.createQuery("from Notification n where n.id = :_id",Notification.class)
                    .setParameter("_id",id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
