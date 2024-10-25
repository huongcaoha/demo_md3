package com.ra.base_spring_mvc.model.service.orderDetail;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getListByOrder(int user_id , int order_id);
    boolean addOrderDetail(List<ShoppingCart> shoppingCarts, Order order);
    boolean updateOrderDetail(OrderDetail orderDetail);
    boolean deleteOrderDetail(OrderDetail orderDetail);
    OrderDetail findById(int order_detail_id);
    List<OrderDetail> getAll();
    List<OrderDetail> getListByOrderId(int order_id);
}
