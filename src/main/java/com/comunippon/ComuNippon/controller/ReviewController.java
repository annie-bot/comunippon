package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Review;
import com.comunippon.ComuNippon.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Review reviewDTO) {
        service.createReview(reviewDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> list() {
        return ResponseEntity.ok(service.listReview());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteReview(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody Review reviewDTO) {
        return ResponseEntity.ok(service.updateReview(id, reviewDTO));
    }

}
