package com.ra.base_spring_mvc.model.service.order;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;

import java.util.List;

public interface OrderService {
    List<Order> getListOrder();
    List<Order> getListByUser(int user_id);
    Order addOrder(Order order);
    Order findById(int order_id);
    boolean updateStatus(int order_id);
    boolean updateOrder(Order order);
    boolean cancelOrder(Order order);
    List<Order> getListPagination(int page , int size);
}
