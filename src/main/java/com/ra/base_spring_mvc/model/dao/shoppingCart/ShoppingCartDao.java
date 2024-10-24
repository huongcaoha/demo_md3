package com.ra.base_spring_mvc.model.dao.shoppingCart;

import com.ra.base_spring_mvc.model.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    List<ShoppingCart> getListShoppingCart(int id_user);
    boolean addTocart(ShoppingCart shoppingCart);
    boolean updateTocart(ShoppingCart shoppingCart);
    boolean deleteTocart(ShoppingCart shoppingCart);
    ShoppingCart findById(int id);
    boolean deleteAll(int id_user);
}
