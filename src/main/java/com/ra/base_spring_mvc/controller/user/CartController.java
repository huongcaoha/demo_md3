package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.Product;
import com.ra.base_spring_mvc.model.entity.ProductDetail;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import com.ra.base_spring_mvc.model.entity.User;
import com.ra.base_spring_mvc.model.service.ShoppingCart.ShoppingCartService;
import com.ra.base_spring_mvc.model.service.product.ProductService;
import com.ra.base_spring_mvc.model.service.productdetail.color.ColorService;
import com.ra.base_spring_mvc.model.service.productdetail.size.SizeService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final ColorService colorService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final SizeService sizeService;
    @Autowired
    private final UserService userService;

    public CartController(ShoppingCartService shoppingCartService, ColorService colorService, ProductService productService, SizeService sizeService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.colorService = colorService;
        this.productService = productService;
        this.sizeService = sizeService;
        this.userService = userService;
    }

    @GetMapping
    public String displayCart(Model model, HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(user.getId());
        model.addAttribute("shoppingCarts", shoppingCarts);
        return "user/cart/displayCart";
    }


    @GetMapping("/up/{id}")
    public String up(@PathVariable int id){
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(1);
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(shoppingCart1 -> shoppingCart1.getId() == id).findFirst().orElse(null);
        shoppingCart.setQuantity(shoppingCart.getQuantity()+1);
        shoppingCartService.updateTocart(shoppingCart);
        return "redirect:/cart";
    }

    @GetMapping("/down/{id}")
    public String down(@PathVariable int id){
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(1);
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(shoppingCart1 -> shoppingCart1.getId() == id).findFirst().orElse(null);
        if(shoppingCart.getQuantity() > 1){
            shoppingCart.setQuantity(shoppingCart.getQuantity()-1);
            shoppingCartService.updateTocart(shoppingCart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(1);
        ShoppingCart shoppingCart = shoppingCarts.stream().filter(shoppingCart1 -> shoppingCart1.getId() == id).findFirst().orElse(null);
        shoppingCartService.deleteTocart(shoppingCart);
        return "redirect:/cart";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        shoppingCartService.deleteAll(user.getId());
        return "redirect:/cart";
    }

}
