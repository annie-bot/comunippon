package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Comment;
import com.comunippon.ComuNippon.model.Topic;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class TopicService {

    private final TopicRepository repository;

    private final CommentService commentService;

    public void createTopic(Topic topicDTO) {
        Topic topic = new Topic();
        topic.setCreationDate(topicDTO.getCreationDate());
        topic.setContent(topicDTO.getContent());
        topic.setTitle(topicDTO.getTitle());

        repository.save(topic);
    }

    public List<Topic> listTopic() {
        return repository.findAll();
    }

    public Topic getTopicById(Long id) {
        return repository.findById(id).get();
    }

    public Topic deleteTopic(Long id) {
        Topic Topic = getTopicById(id);
        repository.deleteById(id);
        return Topic;
    }

    public Topic updateTopic(Long id, Topic topicDTO) {
        Topic topic = getTopicById(id);
        topic.setTitle(topicDTO.getTitle());
        topic.setCreationDate(topicDTO.getCreationDate());
        topic.setContent(topicDTO.getContent());

        return repository.save(topic);
    }

    public List<Comment> listCommentsFromTopic(Long topicId) {
        Topic topic = getTopicById(topicId);
        return topic.getComments();
    }

    public void createCommentDirectlyInTopic(Long topicId, Comment comment) throws Exception {
        Topic topic = getTopicById(topicId);
        if (Objects.isNull(comment.getText())) {
            throw new Exception("O comentário não pode ser vazio");
        }
        topic.getComments().add(comment);
        repository.save(topic);
    }

    public void deleteComment(Long topicId, Long commentId, User user) throws Exception {
        if (!user.isLogged()) {
            throw new Exception("O usuário precisa estar autenticado!");
        }
        Topic topic = getTopicById(topicId);
        for (int i = 0; i < topic.getComments().size(); i++) {
            if (topic.getComments().get(i).getId() == commentId) {
                topic.getComments().remove(i);
            }
        }
        repository.save(topic);
    }

    public void createAnswer(Long topicId, Long commentId) {
        Topic topic = getTopicById(topicId);
        Comment comment = commentService.getCommentById(commentId);
        topic.getComments().add(comment);
    }

}
