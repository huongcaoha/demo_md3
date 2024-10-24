package com.ra.base_spring_mvc.model.dao.review;

import com.ra.base_spring_mvc.model.entity.Review;
import com.ra.base_spring_mvc.model.entity.User;

import java.util.List;

public interface ReviewDAO {
    List<Review> getListReview();
    boolean addReview(Review review);
    boolean updateReview(Review review);
    boolean deleteReview(Review review);
    Review findById(int id);
    List<Review> getListPagination(int page , int size);
}
