package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.Notification;
import com.ra.base_spring_mvc.model.entity.ShoppingCart;
import com.ra.base_spring_mvc.model.entity.User;
import com.ra.base_spring_mvc.model.service.ShoppingCart.ShoppingCartService;
import com.ra.base_spring_mvc.model.service.banner.BannerService;
import com.ra.base_spring_mvc.model.service.notification.NotificationService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

@RequestMapping("/")

public class HomeController {
    @Autowired
    private final BannerService bannerService;
    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final UserService userService ;
    @Autowired
    private final NotificationService notificationService ;

    public HomeController(BannerService bannerService, ShoppingCartService shoppingCartService, UserService userService, NotificationService notificationService) {
        this.bannerService = bannerService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public String indexhome(HttpServletRequest request){
        String bannerTop = "/uploads/" + bannerService.getBannerByLocation("top").getImage();
        String bannerLeft = "/uploads/" + bannerService.getBannerByLocation("left").getImage();
        String bannerRight = "/uploads/" + bannerService.getBannerByLocation("right").getImage();
       HttpSession session = request.getSession();
        User user = new User();
        if(session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
        }else {
            user = userService.findById(1);
        }

        List<ShoppingCart> shoppingCarts = shoppingCartService.getListShoppingCart(user.getId());
        int countCart = shoppingCarts.stream().map(ShoppingCart::getQuantity).reduce(0, Integer::sum);
       session.setAttribute("bannerTop",bannerTop);
       session.setAttribute("bannerLeft",bannerLeft);
       session.setAttribute("bannerRight",bannerRight);
       session.setAttribute("countCart",countCart);
       session.setAttribute("user",user);
        List<Notification> notifications = notificationService.getNotificationByUser(user.getId());
        session.setAttribute("notifications",notifications);
        int countNewNotification = (int) notifications.stream().filter(Notification::isStatus).count();
        session.setAttribute("countNewNotification",countNewNotification);
        return "user/home";
    }

    @GetMapping("/notification/{id}")
    public String hanlerNotification(@PathVariable int id){
        Notification notification = notificationService.findById(id);
        if(notification != null){
            notification.setStatus(false);
            notificationService.updateNotification(notification);
        }
        return "redirect:/";
    }
}
