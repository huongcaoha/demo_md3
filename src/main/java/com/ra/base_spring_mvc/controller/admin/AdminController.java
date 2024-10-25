package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.constant.StatusEnum;
import com.ra.base_spring_mvc.model.service.order.OrderService;
import com.ra.base_spring_mvc.model.service.orderDetail.OrderDetailService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private final UserService userService ;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final OrderService orderService;
    public AdminController(UserService userService, OrderDetailService orderDetailService, OrderService orderService) {
        this.userService = userService;
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
    }

    @GetMapping
    public String index(Model model){
        int totalUser = userService.getListUser().size();
        int totalProductSold = orderDetailService.getAll().
                stream().map(OrderDetail::getQuantity).reduce(0, Integer::sum);
        List<Order> orders = orderService.getListOrder().stream()
                .filter(order -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(order.getCreated_at());
                    int year = calendar.get(Calendar.YEAR);
                    return year == 2024; // Lọc các đơn hàng trong năm 2024
                }).collect(Collectors.toList());
        double totalMoney = orders.stream().filter(order -> order.getStatus().equals(StatusEnum.SUCCESS)).
                map(Order::getTotal_price).reduce(0.0,Double::sum);
        int totalOrder = orderService.getListOrder().size();
        model.addAttribute("totalOrder",totalOrder);
        model.addAttribute("totalMoney",totalMoney);
        model.addAttribute("totalProductSold",totalProductSold);
        model.addAttribute("totalUser",totalUser);
        return "admin/dashboard";
    }
}
