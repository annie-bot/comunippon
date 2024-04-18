package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Review;
import com.comunippon.ComuNippon.model.Title;
import com.comunippon.ComuNippon.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Title")
public class TitleController {

    private TitleService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Title titleDTO) {
        service.createTitle(titleDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Title>> list() {
        return ResponseEntity.ok(service.listTitle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Title> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTitleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteTitle(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Title> update(@PathVariable Long id, @RequestBody Title titleDTO) {
        return ResponseEntity.ok(service.updateTitle(id, titleDTO));
    }

    @GetMapping("/{titleId}/review")
    public ResponseEntity<List<Review>> getReviewsByTitle(@PathVariable Long titleId) {
        return ResponseEntity.ok(service.getReviewByTitle(titleId));
    }

    @PostMapping("/{titleId}/review")
    public  ResponseEntity<Void> postReview(@PathVariable Long titleId, Review review) {
        service.postReview(titleId, review);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{titleId}/review")
    public ResponseEntity<Review> editReview(@PathVariable Long titleId, Review review) throws Exception {
        return ResponseEntity.ok(service.updateReview(titleId, review));
    }

    @DeleteMapping("/{titleId}resenha/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long titleId, @PathVariable Long reviewId) {
        service.deleteReview(titleId, reviewId);
        return new ResponseEntity<>(OK);
    }

}
