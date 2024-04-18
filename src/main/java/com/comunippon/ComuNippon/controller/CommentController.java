package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Comment;
import com.comunippon.ComuNippon.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Comment CommentDTO) {
        service.createComment(CommentDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> list() {
        return ResponseEntity.ok(service.listComment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteComment(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment commentDTO) {
        return ResponseEntity.ok(service.updateComment(id, commentDTO));
    }

}
