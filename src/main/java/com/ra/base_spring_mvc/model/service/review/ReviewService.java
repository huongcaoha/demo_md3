package com.ra.base_spring_mvc.model.service.review;

import com.ra.base_spring_mvc.model.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {
    List<Review> getListReview();
    boolean addReview(Review review);
    boolean updateReview(Review review);
    boolean deleteReview(Review review);
    Review findById(int id);
    List<Review> getListPagination(int page , int size);
}
