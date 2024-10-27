package com.ra.base_spring_mvc.controller;

import com.ra.base_spring_mvc.model.constants.RoleName;
import com.ra.base_spring_mvc.model.entity.User;
import com.ra.base_spring_mvc.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping
public class AuthController
{

    @Autowired
    private UserService userService;

    @GetMapping
    public String index()
    {
        return "admin/dashboard";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/admin/register")
    public String handleRegister(@ModelAttribute("user") User user)
    {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("user", new User());
        return "/admin/login/login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, Model model,  HttpSession session)
    {
        try
        {
            User userLogin = userService.login(user);
            if (userLogin != null)
            {
                // check role (quyền)
//                if (userLogin.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN)))
                if (userLogin.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ADMIN)))
                {
                    return "/admin/dashboard";
                }
                else
                {
                    return "redirect:/user/home";
                }
            }
            return "redirect:/login";
        }
        catch (Exception e)
        {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username or password is incorrect");
            return "/admin/login/login";
        }
    }
}



