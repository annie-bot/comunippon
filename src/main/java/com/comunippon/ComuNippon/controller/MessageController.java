package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Message;
import com.comunippon.ComuNippon.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {

    private MessageService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Message messageDTO) {
        service.createMessage(messageDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Message>> list() {
        return ResponseEntity.ok(service.listMessage());
    }

    @GetMapping("/{id}/{messageId}")
    public ResponseEntity<Message> findById(@PathVariable Long messageId, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.getMessageById(id, messageId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        service.deleteMessage(id);
        return new ResponseEntity<>(OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Message messageDTO) throws Exception {
        return ResponseEntity.ok(service.updateMessage(id, messageDTO));
    }

}
