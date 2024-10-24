package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController
{
    @GetMapping
    public String displayProfile(Model model , HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = new User();
        if(session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            model.addAttribute("user",user);
            return  "user/profile/displayProfile" ;
        }else {
            return "user/login";
        }

    }
}
