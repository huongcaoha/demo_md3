package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Notification;
import com.ra.base_spring_mvc.model.entity.Order;
import com.ra.base_spring_mvc.model.entity.OrderDetail;
import com.ra.base_spring_mvc.model.entity.constant.StatusEnum;
import com.ra.base_spring_mvc.model.entity.dto.OrderSearch;
import com.ra.base_spring_mvc.model.service.notification.NotificationService;
import com.ra.base_spring_mvc.model.service.order.OrderService;
import com.ra.base_spring_mvc.model.service.orderDetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ra.base_spring_mvc.model.entity.constant.StatusEnum.DENIED;

@Controller
@RequestMapping("/order")
public class AdminOrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final OrderDetailService orderDetailService ;
    @Autowired
    private final NotificationService notificationService;

    public AdminOrderController(OrderService orderService, OrderDetailService orderDetailService, NotificationService notificationService, NotificationService notificationService1) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
        this.notificationService = notificationService1;
    }

    @GetMapping
    public String displayOrder(Model model, @RequestParam(value = "page",defaultValue = "1") int page,
                               @RequestParam(value = "size",defaultValue = "5") int size ,
                               HttpServletRequest request){
        List<Order> orders = orderService.getListPagination(page, size);
        OrderSearch orderSearch = (OrderSearch) request.getSession().getAttribute("searchOrder");

        if(orderSearch != null){
            if(!orderSearch.getSearchContent().isEmpty()){
              orders =  orders.stream().filter(order -> order.getReceive_name().equalsIgnoreCase(orderSearch.getSearchContent())
                || order.getReceive_phone().equalsIgnoreCase(orderSearch.getSearchContent())).collect(Collectors.toList());
            }

            if(orderSearch.getStatusOrder() != null){
                orders = orders.stream().filter(order -> order.getStatus().equals(orderSearch.getStatusOrder())).collect(Collectors.toList());
            }

            if (orderSearch.getDate() != null) {
                LocalDate searchDate = orderSearch.getDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Date sang LocalDate

                orders = orders.stream()
                        .filter(order -> order.getCreated_at().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate().equals(searchDate))
                        .collect(Collectors.toList());
            }
        }
        double totalPage = Math.ceil((double) orders.size() / size);
        List<StatusEnum> statusEnums = new ArrayList<>();
        statusEnums.add(StatusEnum.WAITING);
        statusEnums.add(StatusEnum.CONFIRM);
        statusEnums.add(StatusEnum.DELIVERY);
        statusEnums.add(StatusEnum.SUCCESS);
        statusEnums.add(StatusEnum.CANCEL);
        statusEnums.add(DENIED);
        model.addAttribute("statusEnums",statusEnums);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("orders",orders);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("searchOrder",orderSearch != null? orderSearch : new OrderSearch());
        return "admin/order/displayOrder";
    }
    @PostMapping
    public String hanlerSearch(@ModelAttribute("seachOrder") OrderSearch orderSearch, HttpServletRequest request){
        request.getSession().setAttribute("searchOrder",orderSearch);
        return "redirect:/order";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id){
        orderService.updateStatus(id);
        return "redirect:/order";
    }

    @GetMapping("/denied/{id}")
    public String deniedOrder(@PathVariable int id){
        Order order = orderService.findById(id);
        order.setStatus(DENIED);
        orderService.updateOrder(order);
        Notification notification = new Notification();
        notification.setUser(order.getUser());
        notification.setContent("Order code " + order.getSerial_number() +" has had its status updated to: Denied");
        notificationService.addNotification(notification);
        return "redirect:/order";
    }

    @GetMapping("/viewDetail/{id_order}")
    public String orderDetail(@PathVariable int id_order, Model model){
        List<OrderDetail> orderDetails = orderDetailService.getListByOrderId(id_order);
        model.addAttribute("orderDetails",orderDetails);
        return "admin/order/orderDetail" ;
    }
}
