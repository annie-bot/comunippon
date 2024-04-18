package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Comment;
import com.comunippon.ComuNippon.model.Topic;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Topic")
public class TopicController {

    private TopicService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Topic topicDTO) {
        service.createTopic(topicDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> list() {
        return ResponseEntity.ok(service.listTopic());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTopicById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteTopic(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> update(@PathVariable Long id, @RequestBody Topic topicDTO) {
        return ResponseEntity.ok(service.updateTopic(id, topicDTO));
    }

    @GetMapping("/{topicId}/comment")
    public ResponseEntity<List<Comment>> listCommentsFromTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(service.listCommentsFromTopic(topicId));
    }

    @PostMapping("/{topicId}/comment")
    public ResponseEntity<Void> createCommentDirectlyInTopic(@PathVariable Long topicId, Comment comment) throws Exception {
        service.createCommentDirectlyInTopic(topicId, comment);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{topicId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long topicId, @PathVariable Long commentId, @RequestBody User user) throws Exception {
        service.deleteComment(topicId, commentId, user);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("/{topicId}/comment/{commentId}")
    public ResponseEntity<Void> createAnswer(@PathVariable Long topicId, @PathVariable Long commentId) {
        service.createAnswer(topicId, commentId);
        return new ResponseEntity<>(CREATED);
    }




}
