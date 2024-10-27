package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Review;
import com.ra.base_spring_mvc.model.entity.dto.ReviewSearchDto;
import com.ra.base_spring_mvc.model.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    public String index(Model model, @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size,
                        HttpServletRequest request){
        List<Review> reviews = reviewService.getListPagination(page, size);
        double totalPage = Math.ceil((double) reviewService.getListReview().size() / size);
        ReviewSearchDto reviewSearchDto = (ReviewSearchDto) request.getSession().getAttribute("reviewSearch");

        if(reviewSearchDto != null){
            if(!reviewSearchDto.getProductName().isEmpty()){
                reviews =  reviews.stream().filter(review -> review.getProductDetail()
                        .getProduct().getProductName().equalsIgnoreCase(reviewSearchDto.getProductName()))
                        .collect(Collectors.toList());
            }

            if(reviewSearchDto.getRate() > 0){
                reviews = reviews.stream().filter(review -> review.getRate() == reviewSearchDto.getRate())
                        .collect(Collectors.toList());
            }

            if (reviewSearchDto.getCreated_at() != null) {
                LocalDate searchDate = reviewSearchDto.getCreated_at().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Date sang LocalDate

                reviews = reviews.stream()
                        .filter(review -> review.getCreated_at().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate().equals(searchDate))
                        .collect(Collectors.toList());
            }
        }
        model.addAttribute("reviews",reviews);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("reviewSearch",reviewSearchDto != null ? reviewSearchDto : new ReviewSearchDto());
        return "admin/review/displayReview";
    }

    @PostMapping
    public String hanlerSearch(@ModelAttribute("reviewSearch") ReviewSearchDto reviewSearchDto,
                               HttpServletRequest request){
        request.getSession().setAttribute("reviewSearch",reviewSearchDto);
        return "redirect:/review";
    }
}
