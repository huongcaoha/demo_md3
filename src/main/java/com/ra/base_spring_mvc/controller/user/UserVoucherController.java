package com.ra.base_spring_mvc.controller.user;

import com.ra.base_spring_mvc.model.entity.User;
import com.ra.base_spring_mvc.model.entity.Voucher;
import com.ra.base_spring_mvc.model.entity.VoucherUser;
import com.ra.base_spring_mvc.model.entity.dto.VoucherDtoDisplay;
import com.ra.base_spring_mvc.model.service.user.UserService;
import com.ra.base_spring_mvc.model.service.voucher.VoucherService;
import com.ra.base_spring_mvc.model.service.voucherUser.VoucherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userVoucher")
public class UserVoucherController {
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private VoucherUserService voucherUserService;
    @Autowired
    private UserService userService;
    @GetMapping
    public String getVoucher(Model model,HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        List<Voucher> vouchers = voucherService.getListVoucher();
        vouchers = vouchers.stream().filter(voucher -> !voucher.getStart_date().after(new Date()) &&
                !voucher.getEnd_date().before(new Date())).collect(Collectors.toList());
        List<VoucherDtoDisplay> voucherDtoDisplays = voucherService.converseDtoToVoucher(vouchers, user.getId());
        model.addAttribute("vouchers",voucherDtoDisplays);
        return "user/getVoucher";
    }

    @GetMapping("/getVoucher/{id}")
    public String getVoucher(@PathVariable int id , HttpServletRequest request){
        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            user = (User) request.getSession().getAttribute("user");
        }else {
            user = userService.findById(1);
        }
        if(!voucherUserService.checkVoucherExist(user.getId(),id)){
            Voucher voucher = voucherService.findById(id);
            VoucherUser voucherUser = new VoucherUser();
            voucherUser.setVoucher(voucher);
            voucherUser.setUser(user);
            voucherUser.setStatus(true);
            voucherUserService.addVoucherUser(voucherUser);
        }
        return "redirect:/userVoucher";
    }
}
