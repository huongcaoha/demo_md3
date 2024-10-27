package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.service.category.CategoryService;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    public UserService userService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/login")
    public String adminHome(Model model) {

        model.addAttribute("categoryList",categoryService.getListCategory());
        model.addAttribute("userList",userService.getListUser());
//        model.addAttribute("user", currentUser);
        return "admin/dashboard";
    }
}
