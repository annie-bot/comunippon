package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Message;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    private final UserService userService;

    public void createMessage(Message messageDTO) {
        Message message = new Message();
        message.setSendDate(messageDTO.getSendDate());
        message.setText(messageDTO.getText());

        repository.save(message);
    }

    public List<Message> listMessage() {
        return repository.findAll();
    }

    public Message getMessageById(Long id, Long messageId) throws Exception {
        if (!repository.findById(messageId).isPresent()) {
            throw new Exception("Mensagem não encontrada");
        }
        User user = userService.getUserById(id);

        return repository.findByUser(user).get();
    }

    private Message checkMessage(Long id) throws Exception {
        if (repository.findById(id).isEmpty()) {
            throw new Exception("Messagem não encontrada");
        }
        Message message = repository.findById(id).get();
        return message;
    }
    public Message deleteMessage(Long id) throws Exception {

        Message message = checkMessage(id);
        repository.deleteById(id);
        return message;
    }

    public Message updateMessage(Long id, Message messageDTO) throws Exception {
        Message message = checkMessage(id);
        message.setSendDate(messageDTO.getSendDate());
        message.setText(messageDTO.getText());
        return repository.save(message);
    }

}
