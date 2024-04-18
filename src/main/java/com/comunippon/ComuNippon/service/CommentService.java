package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Comment;
import com.comunippon.ComuNippon.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public void createComment(Comment commentDTO) {
        Comment comment = new Comment();
        comment.setCreationDate(commentDTO.getCreationDate());
        comment.setText(commentDTO.getText());

        repository.save(comment);
    }

    public List<Comment> listComment() {
        return repository.findAll();
    }

    public Comment getCommentById(Long id) {
        return repository.findById(id).get();
    }

    public Comment deleteComment(Long id) {
        Comment comment = getCommentById(id);
        repository.deleteById(id);
        return comment;
    }

    public Comment updateComment(Long id, Comment commentDTO) {
        Comment comment = getCommentById(id);
        comment.setCreationDate(commentDTO.getCreationDate());
        comment.setText(commentDTO.getText());
        return repository.save(comment);
    }

}
