package com.ra.base_spring_mvc.model.service.orderDetail;

import com.ra.base_spring_mvc.model.dao.orderDetail.OrderDetailDAO;
import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private final OrderDetailDAO orderDetailDAO;

    public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO) {
        this.orderDetailDAO = orderDetailDAO;
    }

    @Override
    public List<OrderDetail> getListByOrder(int user_id, int order_id) {
        return orderDetailDAO.getListByOrder(user_id, order_id);
    }

    @Override
    public boolean addOrderDetail(List<ShoppingCart> shoppingCarts, Order order) {
        return orderDetailDAO.addOrderDetail(shoppingCarts,order);
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailDAO.updateOrderDetail(orderDetail);
    }

    @Override
    public boolean deleteOrderDetail(OrderDetail orderDetail) {
        return orderDetailDAO.deleteOrderDetail(orderDetail);
    }

    @Override
    public OrderDetail findById(int order_detail_id) {
        return orderDetailDAO.findById(order_detail_id);
    }
}
