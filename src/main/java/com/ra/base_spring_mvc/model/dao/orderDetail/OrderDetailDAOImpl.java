package com.ra.base_spring_mvc.model.dao.orderDetail;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import com.ra.base_spring_mvc.model.service.order.OrderService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Autowired
    private final SessionFactory sessionFactory;
    @Autowired
    private final OrderService orderService;

    public OrderDetailDAOImpl(SessionFactory sessionFactory, OrderService orderService) {
        this.sessionFactory = sessionFactory;
        this.orderService = orderService;
    }


    @Override
    public List<OrderDetail> getListByOrder(int user_id ,int order_id) {
        List<Order> orders = orderService.getListByUser(user_id);
        List<OrderDetail> orderDetails = new ArrayList<>();
        if(orders.stream().map(Order::getId).anyMatch(id -> id == order_id)){
          try (Session session = sessionFactory.openSession()){
             orderDetails = session.createQuery("from OrderDetail od where od.order.id = :_id", OrderDetail.class)
                      .setParameter("_id",order_id).list();
          }catch (Exception e){
              e.printStackTrace();
          }
          return orderDetails;
        }else {
            return null ;
        }
    }

    @Override
    public boolean addOrderDetail(List<ShoppingCart> shoppingCarts,Order order) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            for(ShoppingCart sc : shoppingCarts){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setQuantity(sc.getQuantity());
                orderDetail.setProductDetail(sc.getProductDetail());
                orderDetail.setOrder(order);
                session.save(orderDetail);
            }
            session.getTransaction().commit();
            return  true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(orderDetail);
            session.getTransaction().commit();
            return  true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteOrderDetail(OrderDetail orderDetail) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(orderDetail);
            session.getTransaction().commit();
            return  true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public OrderDetail findById(int order_detail_id) {
        try (Session session = sessionFactory.openSession()){
           return session.createQuery("from OrderDetail od where od.id = :_id", OrderDetail.class)
                    .setParameter("_id",order_detail_id).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}
