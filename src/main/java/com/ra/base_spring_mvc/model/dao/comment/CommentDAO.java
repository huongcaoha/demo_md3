package com.ra.base_spring_mvc.model.dao.comment;

import com.ra.base_spring_mvc.model.entity.Comment;
import com.ra.base_spring_mvc.model.entity.Review;

import java.util.List;

public interface CommentDAO {
    List<Comment> getListComment();
    boolean addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(Comment comment);
    Comment findById(int id);
    List<Comment> getListPagination(int page , int size);
}
