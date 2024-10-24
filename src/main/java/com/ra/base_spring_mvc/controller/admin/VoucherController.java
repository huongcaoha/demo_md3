package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.Voucher;
import com.ra.base_spring_mvc.model.entity.dto.BannerDto;
import com.ra.base_spring_mvc.model.entity.dto.VoucherDto;
import com.ra.base_spring_mvc.model.service.voucher.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public String index(@RequestParam(value = "page",defaultValue = "1") int page ,
                        @RequestParam(value = "size",defaultValue = "10") int size ,
                        Model model
    ){
        List<Voucher> vouchers = voucherService.getListPagination(page, size);
        double totalPage = Math.ceil((double) voucherService.getListVoucher().size() / size);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("vouchers",vouchers);
        return "admin/voucher/voucher";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("voucher",new VoucherDto());
        return "admin/voucher/addVoucher";
    }


    @PostMapping("/add")
    public String create(@ModelAttribute("voucher") @Valid VoucherDto voucherDto, RedirectAttributes redirectAttributes
    , BindingResult result) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("voucher", voucherDto);
            redirectAttributes.addFlashAttribute("error", "create new error !");
            return "redirect:/voucher/add";
        }
        Voucher voucher = voucherService.converseVoucherDto(voucherDto);
        if (voucherService.addVoucher(voucher)) {
            return "redirect:/voucher";
        } else {
            redirectAttributes.addFlashAttribute("voucher", voucherDto);
            redirectAttributes.addFlashAttribute("error", "create new error !");
            return "redirect:/voucher/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Voucher voucher = voucherService.findById(id);
        VoucherDto voucherDto = voucherService.converseVoucher(voucher);
        model.addAttribute("voucher",voucherDto);
        return "admin/voucher/updateVoucher" ;
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute("voucher") VoucherDto voucherDto , RedirectAttributes redirectAttributes){
        Voucher oldVoucher = voucherService.findById(id);
        Voucher voucher = voucherService.converseVoucherDto(voucherDto);
        voucher.setId(id);
        if(voucherService.updateVoucher(voucher)){
            return "redirect:/voucher";
        }else {
            redirectAttributes.addFlashAttribute("voucher", voucherDto);
            redirectAttributes.addFlashAttribute("error", "Update voucher error !");
            return "redirect:/voucher/edit/" + id ;
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id , Model model){
        Voucher voucher = voucherService.findById(id);
        if(!voucherService.deleteVoucher(voucher)){
            model.addAttribute("error","Delete voucher error !");
        }
        return "redirect:/voucher";
    }
}
