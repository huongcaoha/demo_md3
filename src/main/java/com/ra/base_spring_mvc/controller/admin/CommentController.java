package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Comment;
import com.ra.base_spring_mvc.model.entity.dto.CommentSearchDto;
import com.ra.base_spring_mvc.model.entity.dto.ReviewSearchDto;
import com.ra.base_spring_mvc.model.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService ;

    @GetMapping
    public String index(Model model , @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size,
                        HttpServletRequest request){

        List<Comment> comments = commentService.getListPagination(page, size);
        double totalPage = Math.ceil((double) commentService.getListComment().size() / size);
        CommentSearchDto commentSearchDto = (CommentSearchDto) request.getSession().getAttribute("commentSearch");
        if(commentSearchDto != null){
            if(!commentSearchDto.getProductName().isEmpty()){
                comments =  comments.stream().filter(comment -> comment.getProductDetail()
                                .getProduct().getProductName().equalsIgnoreCase(commentSearchDto.getProductName()))
                        .collect(Collectors.toList());
            }

            if(commentSearchDto.getStatus() != null){
                comments = comments.stream().filter(comment -> comment.isStatus() == commentSearchDto.getStatus())
                        .collect(Collectors.toList());
            }

            if (commentSearchDto.getCreated_at() != null) {
                LocalDate searchDate = commentSearchDto.getCreated_at().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Date sang LocalDate

                comments = comments.stream()
                        .filter(comment -> comment.getCreated_at().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate().equals(searchDate))
                        .collect(Collectors.toList());
            }
        }
        model.addAttribute("commentSearch",commentSearchDto != null ? commentSearchDto : new CommentSearchDto());
        model.addAttribute("comments",comments);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",totalPage);
        return "admin/comment/displayComment";
    }

    @GetMapping("/update/{id}")
    public String updateStatus(@PathVariable int id){
        Comment comment = commentService.findById(id);
            comment.setStatus(!comment.isStatus());
        commentService.updateComment(comment);
        return "redirect:/comment";
    }

    @PostMapping
    public String hanlerSearch(@ModelAttribute("commentSearch") CommentSearchDto commentSearchDto,
                               HttpServletRequest request){
        request.getSession().setAttribute("commentSearch",commentSearchDto);
        return "redirect:/comment";
    }
}
