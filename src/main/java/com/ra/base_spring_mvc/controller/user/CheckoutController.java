package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.*;
import com.ra.base_spring_mvc.model.entity.dto.OrderDto;
import com.ra.base_spring_mvc.model.service.ShoppingCart.ShoppingCartService;
import com.ra.base_spring_mvc.model.service.order.OrderService;
import com.ra.base_spring_mvc.model.service.orderDetail.OrderDetailService;
import com.ra.base_spring_mvc.model.service.productdetail.ProductDetailService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import com.ra.base_spring_mvc.model.service.voucher.VoucherService;
import com.ra.base_spring_mvc.model.service.voucherUser.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private final UserService userService ;
    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final VoucherUserService voucherUserService;
    @Autowired
    private final OrderService orderService ;
    @Autowired
    private final VoucherService voucherService;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final ProductDetailService productDetailService;

    public CheckoutController(UserService userService, ShoppingCartService shoppingCartService, VoucherUserService voucherUserService, OrderService orderService, VoucherService voucherService, OrderDetailService orderDetailService, ProductDetailService productDetailService) {
        this.userService = userService;

        this.shoppingCartService = shoppingCartService;
        this.voucherUserService = voucherUserService;

        this.orderService = orderService;
        this.voucherService = voucherService;
        this.orderDetailService = orderDetailService;
        this.productDetailService = productDetailService;
    }

    @GetMapping
    private String checkout(HttpServletRequest request, Model model){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(user.getId());
        List<VoucherUser> voucherUsers = voucherUserService.getVoucherByUser(user.getId());
        voucherUsers = voucherUsers.stream()
                .filter(voucherUser -> voucherUser.isStatus()
                        && !voucherUser.getVoucher().getStart_date().after(new Date())
                        && !voucherUser.getVoucher().getEnd_date().before(new Date()))
                .collect(Collectors.toList());
        double totalMoney = shoppingCarts.stream().map(shoppingCart -> shoppingCart.getQuantity() * shoppingCart.getProductDetail().getUnit_price())
                        .reduce((double) 0, Double::sum);
        model.addAttribute("totalMoney",totalMoney);
        model.addAttribute("voucherUsers",voucherUsers);
        model.addAttribute("shoppingCarts",shoppingCarts);
        model.addAttribute("user",user);
        model.addAttribute("order",new OrderDto());
        return "user/checkout/checkout";
    }

    @PostMapping
    public String payment(@ModelAttribute("order") OrderDto orderDto , HttpServletRequest request ,
                          @RequestParam("voucher_id") int voucher_id){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(user.getId());
        boolean checkPay = true ;
        for(ShoppingCart sc : shoppingCarts){
            ProductDetail productDetail = productDetailService.findById(sc.getProductDetail().getId());
            if(productDetail.getStock_quantity() < sc.getQuantity()){
                checkPay = false;
                break;
            }
        }
        if(checkPay){
            double totalMoney = shoppingCarts.stream().map(shoppingCart -> shoppingCart.getQuantity() * shoppingCart.getProductDetail().getUnit_price())
                    .reduce((double) 0, Double::sum);
            Voucher voucher = voucherService.findById(voucher_id);
            Order order = new Order();
            order.setReceive_name(orderDto.getReceive_name());
            order.setReceive_address(orderDto.getReceive_address());
            order.setNote(orderDto.getNote());
            order.setReceive_phone(orderDto.getReceive_phone());
            order.setUser(user);
            order.setVoucher(voucher);
            if(voucher != null){
                order.setTotal_price(totalMoney - (totalMoney * voucher.getPersent() ) / 100);
            }else {
                order.setTotal_price(totalMoney);
            }

            Order newOrder = orderService.addOrder(order) ;
            if(newOrder != null){
                boolean rs = orderDetailService.addOrderDetail(shoppingCarts,newOrder);
                if(rs){
                    for(ShoppingCart sc : shoppingCarts){
                        ProductDetail productDetail = productDetailService.findById(sc.getProductDetail().getId());
                        productDetail.setStock_quantity(productDetail.getStock_quantity() - sc.getQuantity());
                        productDetailService.updateProductDetail(productDetail);
                    }
                    VoucherUser voucherUser = voucherUserService.getListVoucherByUser(user.getId()).stream().
                            filter(voucherUser1 -> voucherUser1.getVoucher().getId() == voucher_id).findFirst().orElse(null);
                    if(voucherUser != null){
                        voucherUser.setStatus(false);
                        voucherUserService.updateVoucherUser(voucherUser);
                    }
                    shoppingCartService.deleteAll(user.getId());
                    return "redirect:/";
                }else {
                    return "redirect:/checkout";
                }
            }else {
                return "redirect:/checkout";
            }
        }else {
            return "redirect:/checkout";
        }

    }
}
