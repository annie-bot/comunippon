package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Title;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<Void> create(@RequestBody User userDTO) {
        service.createUser(userDTO);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signin(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(service.signIn(user));
    }

    @GetMapping("resetPassword/{email}")
    public ResponseEntity<User> resetPassword(@PathVariable String email){
        return ResponseEntity.ok(service.resetPassword(email));
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.listUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        service.deleteUser(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userDTO) {
        return ResponseEntity.ok(service.updateUser(id, userDTO));
    }

    @PostMapping("/titleList/{listId}")
    public ResponseEntity<Void> addTitle(@PathVariable Long listId, @RequestBody User user, @RequestBody Title title) {
        service.addTitle(listId, user, title);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/titleList/{listId}")
    public ResponseEntity<Void> deleteTitleFromList(@PathVariable Long listId) throws Exception {
        service.deleteTitleFromList(listId);
        return new ResponseEntity<>(OK);
    }

}
