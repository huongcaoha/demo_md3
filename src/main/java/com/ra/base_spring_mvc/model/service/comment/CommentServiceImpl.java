package com.ra.base_spring_mvc.model.service.comment;

import com.ra.base_spring_mvc.model.dao.comment.CommentDAO;
import com.ra.base_spring_mvc.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO ;
    @Override
    public List<Comment> getListComment() {
        return commentDAO.getListComment();
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentDAO.updateComment(comment);
    }

    @Override
    public boolean deleteComment(Comment comment) {
        return commentDAO.deleteComment(comment);
    }

    @Override
    public Comment findById(int id) {
        return commentDAO.findById(id);
    }

    @Override
    public List<Comment> getListPagination(int page, int size) {
        return commentDAO.getListPagination(page, size);
    }
}
