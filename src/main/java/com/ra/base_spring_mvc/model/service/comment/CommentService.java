package com.ra.base_spring_mvc.model.service.comment;

import com.ra.base_spring_mvc.model.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    List<Comment> getListComment();
    boolean addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(Comment comment);
    Comment findById(int id);
    List<Comment> getListPagination(int page , int size);

}
