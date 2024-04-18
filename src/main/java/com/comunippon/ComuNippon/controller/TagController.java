package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Tag;
import com.comunippon.ComuNippon.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/Tags")
public class TagController {

    private TagService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Tag tagDTO) {
        service.createTag(tagDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> list() {
        return ResponseEntity.ok(service.listTag());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTagById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteTag(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@PathVariable Long id, @RequestBody Tag tagDTO) {
        return ResponseEntity.ok(service.updateTag(id, tagDTO));
    }

}
