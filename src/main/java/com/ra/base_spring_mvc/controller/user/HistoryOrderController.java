package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.User;
import com.ra.base_spring_mvc.model.service.order.OrderService;
import com.ra.base_spring_mvc.model.service.orderDetail.OrderDetailService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/historyOrder")
public class HistoryOrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final OrderDetailService orderDetailService;

    public HistoryOrderController(OrderService orderService, UserService userService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public String history(Model model, HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<Order> orders = orderService.getListByUser(user.getId());
        model.addAttribute("orders",orders);
        return "user/profile/historyOrder";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id, Model model){
        Order order = orderService.findById(id);
        boolean rs = orderService.cancelOrder(order);
        if(rs){
            model.addAttribute("rs",true);
        }else {
            model.addAttribute("rs",false);
        }
        return "redirect:/historyOrder";
    }

    @GetMapping("/viewDetail/{id_order}")
    public String orderDetail(@PathVariable int id_order, Model model,HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<OrderDetail> orderDetails = orderDetailService.getListByOrder(user.getId(),id_order);
        model.addAttribute("orderDetails",orderDetails);
        return "user/profile/orderDetail" ;
    }
}
