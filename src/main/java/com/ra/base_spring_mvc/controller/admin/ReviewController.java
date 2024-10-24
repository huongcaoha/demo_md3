package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Review;
import com.ra.base_spring_mvc.model.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    public String index(Model model, @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size){
        List<Review> reviews = reviewService.getListPagination(page, size);
        double totalPage = Math.ceil((double) reviewService.getListReview().size() / size);
        model.addAttribute("reviews",reviews);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",totalPage);
        return "admin/review/displayReview";
    }
}
