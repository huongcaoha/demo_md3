package com.ra.base_spring_mvc.model.service.ShoppingCart;

import com.ra.base_spring_mvc.model.dao.shoppingCart.ShoppingCartDao;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDAO;

    @Override
    public List<ShoppingCart> getListShoppingCart(int id_user) {
        return shoppingCartDAO.getListShoppingCart(id_user);
    }

    @Override
    public boolean addTocart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.addTocart(shoppingCart);
    }

    @Override
    public boolean updateTocart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.updateTocart(shoppingCart);
    }

    @Override
    public boolean deleteTocart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.deleteTocart(shoppingCart);
    }

    @Override
    public ShoppingCart findById(int id) {
        return shoppingCartDAO.findById(id);
    }

    @Override
    public boolean deleteAll(int id_user) {
        return shoppingCartDAO.deleteAll(id_user);
    }
}
