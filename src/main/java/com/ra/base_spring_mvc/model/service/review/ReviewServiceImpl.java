package com.ra.base_spring_mvc.model.service.review;

import com.ra.base_spring_mvc.model.dao.review.ReviewDAO;
import com.ra.base_spring_mvc.model.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewDAO reviewDAO;
    @Override
    public List<Review> getListReview() {
        return reviewDAO.getListReview();
    }

    @Override
    public boolean addReview(Review review) {
        return reviewDAO.addReview(review);
    }

    @Override
    public boolean updateReview(Review review) {
        return reviewDAO.updateReview(review);
    }

    @Override
    public boolean deleteReview(Review review) {
        return reviewDAO.deleteReview(review);
    }

    @Override
    public Review findById(int id) {
        return reviewDAO.findById(id);
    }

    @Override
    public List<Review> getListPagination(int page, int size) {
        return reviewDAO.getListPagination(page, size);
    }
}
