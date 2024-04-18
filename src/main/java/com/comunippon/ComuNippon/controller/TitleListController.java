package com.comunippon.ComuNippon.controller;

import com.comunippon.ComuNippon.model.Review;
import com.comunippon.ComuNippon.model.Title;
import com.comunippon.ComuNippon.model.TitleList;
import com.comunippon.ComuNippon.service.TitleListService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/TitleLists")
public class TitleListController {

    private TitleListService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TitleList titleListDTO) {
        service.createTitleList(titleListDTO);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TitleList>> list() {
        return ResponseEntity.ok(service.listTitleList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleList> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTitleListById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
        service.deleteTitleList(id);
        return new ResponseEntity<>(OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleList> update(@PathVariable Long id, @RequestBody TitleList titleListDTO) {
        return ResponseEntity.ok(service.updateTitleList(id, titleListDTO));
    }

}
