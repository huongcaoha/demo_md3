package com.ra.base_spring_mvc.model.dao.order;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import com.ra.base_spring_mvc.model.entity.constant.StatusEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{
    @Autowired
    private final SessionFactory sessionFactory;


    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Order> getListOrder() {
        List<Order> orders = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           orders = session.createQuery("from Order ", Order.class).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getListByUser(int user_id) {
        List<Order> orders = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
           orders = session.createQuery("from Order o where o.user.id = :id", Order.class)
                    .setParameter("id",user_id).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order addOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
           int id = (int) session.save(order);
            session.getTransaction().commit();
            return findById(id);

        }catch (Exception e){
          session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            session.close();
        }
        return null ;
    }



    @Override
    public Order findById(int order_id) {
        try (Session session = sessionFactory.openSession()){
           return session.createQuery("from Order o where o.id = :_id", Order.class)
                    .setParameter("_id", order_id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateStatus(int order_id) {
        Order order = findById(order_id);
        switch (order.getStatus()){
            case WAITING:{
                order.setStatus(StatusEnum.CONFIRM);
                break;
            }
            case CONFIRM:{
                order.setStatus(StatusEnum.DELIVERY);
                break;
            }
            case DELIVERY:{
                order.setStatus(StatusEnum.SUCCESS);
                break;
            }
            case SUCCESS:{
                break;
            }
        }
        return updateOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(order);
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
    public boolean cancelOrder(Order order) {
        order.setStatus(StatusEnum.CANCEL);
        return updateOrder(order);
    }

    @Override
    public List<Order> getListPagination(int page, int size) {
        List<Order> orders = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            orders = session.createQuery("from Order", Order.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

}
