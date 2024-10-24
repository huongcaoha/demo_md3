package com.ra.base_spring_mvc.model.service.order;

import com.ra.base_spring_mvc.model.dao.order.OrderDAO;
import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getListOrder() {
        return orderDAO.getListOrder();
    }

    @Override
    public List<Order> getListByUser(int user_id) {
        return orderDAO.getListByUser(user_id);
    }

    @Override
    public Order addOrder(Order order) {
        return orderDAO.addOrder(order);
    }

    @Override
    public Order findById(int order_id) {
        return orderDAO.findById(order_id);
    }

    @Override
    public boolean updateStatus(int order_id) {
        return orderDAO.updateStatus(order_id);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

}
