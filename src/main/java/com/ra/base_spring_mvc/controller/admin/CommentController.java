package com.ra.base_spring_mvc.controller.admin;

import com.ra.base_spring_mvc.model.entity.Comment;
import com.ra.base_spring_mvc.model.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService ;

    @GetMapping
    public String index(Model model , @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size){

        List<Comment> comments = commentService.getListPagination(page, size);
        double totalPage = Math.ceil((double) commentService.getListComment().size() / size);
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
}
