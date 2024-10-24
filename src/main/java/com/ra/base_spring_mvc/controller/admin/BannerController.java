package com.ra.base_spring_mvc.controller.admin;


import com.ra.base_spring_mvc.model.entity.Banner;
import com.ra.base_spring_mvc.model.entity.dto.BannerDto;
import com.ra.base_spring_mvc.model.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping
    public String index(Model model, @RequestParam(value = "page",defaultValue = "1") int page , @RequestParam(value = "size",defaultValue = "5") int size){
        List<Banner> banners = bannerService.getListPagination(page, size);
        double totalPage = Math.ceil((double) bannerService.getListBanner().size() /size);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("banners",banners);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        return "admin/banner/banner";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("banner",new BannerDto());
        return "admin/banner/addBanner";
    }


    @PostMapping("/add")
    public String create(@ModelAttribute("banner") BannerDto bannerDto, RedirectAttributes redirectAttributes) {
        Banner banner = bannerService.converseBannerDto(bannerDto);
        if (bannerService.addBanner(banner) && banner.getImage() != null) {
            return "redirect:/banner";
        } else {
            redirectAttributes.addFlashAttribute("banner", bannerDto);
            redirectAttributes.addFlashAttribute("error", "create new error !");
            return "redirect:/banner/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Banner banner = bannerService.findById(id);
        BannerDto bannerDto = bannerService.converseBanner(banner);
        model.addAttribute("banner",bannerDto);
        model.addAttribute("image",banner.getImage());
        return "admin/banner/updateBanner" ;
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, Model model , @ModelAttribute("banner") BannerDto bannerDto , RedirectAttributes redirectAttributes){

        Banner oldBanner = bannerService.findById(id);
        Banner banner = bannerService.converseBannerDto(bannerDto);
        banner.setId(id);
        if(banner.getImage() == null){
            banner.setImage(oldBanner.getImage());

        }
        if(bannerService.updateBanner(banner)){
            return "redirect:/banner";
        }else {
            redirectAttributes.addFlashAttribute("banner", bannerDto);
            redirectAttributes.addFlashAttribute("error", "Update banner error !");
            return "redirect:/banner/edit/" + id ;
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id , Model model){
        Banner banner = bannerService.findById(id);
        if(!bannerService.deleteBanner(banner)){
            model.addAttribute("error","Delete bansererr error !");
        }
        return "redirect:/banner";

    }
}
