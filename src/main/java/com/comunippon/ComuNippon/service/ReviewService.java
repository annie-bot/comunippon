package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Review;
import com.comunippon.ComuNippon.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;

    public void createReview(Review reviewDTO) {
        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        repository.save(review);
    }

    public List<Review> listReview() {
        return repository.findAll();
    }

    public Review getReviewById(Long id) {
        return repository.findById(id).get();
    }

    public Review deleteReview(Long id) {
        Review review = getReviewById(id);
        repository.deleteById(id);
        return review;
    }

    public Review updateReview(Long id, Review reviewDTO) {
        Review review = getReviewById(id);
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        return repository.save(review);

    }

}
