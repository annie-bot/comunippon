package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Chat;
import com.comunippon.ComuNippon.model.Message;
import com.comunippon.ComuNippon.model.User;
import com.comunippon.ComuNippon.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class ChatService {

    private final ChatRepository repository;
    private final UserService userService;
    public void createChat(Long id1, Long id2, Message message) {
        User user1 = userService.getUserById(id1);
        User user2 = userService.getUserById(id2);
        if (listAllByUser1AndUser2(id1, id2).size() == 1) {
            listAllByUser1AndUser2(id1, id2).get(0).getMessages().add(message);
        }else {
            Chat chat = new Chat();
            chat.getUsers().add(user1);
            chat.getUsers().add(user2);
            chat.getMessages().add(message);
            repository.save(chat);
        }

    }

    public List<Chat> listChat() {
        return repository.findAll();
    }

    public List<Chat> listAllByUser1AndUser2(Long id1, Long id2) {
        List<Chat> chats = listChat();
        User user1 = userService.getUserById(id1);
        User user2 = userService.getUserById(id2);
        List<Chat> filteredChats = new ArrayList<>();

        for (int i = 0; i < chats.size(); i++) {
            if (chats.contains(user1) && chats.contains(user2)) {
                filteredChats.add(chats.get(i));
            }
        }

        return filteredChats;
    }

    public Chat getChatById(Long id) {
        return repository.findById(id).get();
    }

    public Chat deleteChat(Long id) {
        Chat chat = getChatById(id);
        repository.deleteById(id);
        return chat;
    }

}
