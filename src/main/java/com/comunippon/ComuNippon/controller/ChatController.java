package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Chat;
import com.comunippon.ComuNippon.model.Message;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.service.ChatService;
import com.comunippon.ComuNippon.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private ChatService service;



    @PostMapping("/{id2}")
    public ResponseEntity<Void> create(@RequestBody User user, @PathVariable Long id2, @RequestBody Message message) {
        service.createChat(user.getId(), id2, message);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Chat>> list() {
        return ResponseEntity.ok(service.listChat());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getChatById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteChat(id);
        return new ResponseEntity<>(OK);
    }

}
