package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.*;
import com.comunippon.ComuNippon.repository.MessageRepository;
import com.comunippon.ComuNippon.repository.UserRepository;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;


@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final MessageRepository messageRepository;

    private final TitleListService titleListService;



    public void createUser(User userDTO) {
        User user = new User();
        user.setCreationDate(LocalDate.now());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(user.getPassword());

        repository.save(user);
    }

    public User signIn(User userDTO) throws Exception {
        User user = repository.findUserByUserName(userDTO.getUserName()).get();
        if (repository.findUserByUserName(userDTO.getUserName()).isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }
        user.setLogged(true);
        return user;
    }

    public User signOut(User userDTO) throws Exception {
        User user = repository.findUserByUserName(userDTO.getUserName()).get();
        if (repository.findUserByUserName(userDTO.getUserName()).isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }
        user.setLogged(false);
        return user;
    }

    public List<User> listUser() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    public User deleteUser(Long id) throws Exception {
        User User = getUserById(id);
        if (!User.isLogged()) {
            throw new Exception("Usuário não conectado.");
        }

        repository.deleteById(id);
        return User;
    }

    public User updateUser(Long id, User userDTO) {
        User user = getUserById(id);
        if (!user.getPassword().equals(userDTO.getPassword())) {
            user.setPassword(userDTO.getPassword());
        }

        if (!user.getUserName().equals(userDTO.getUserName())) {
            user.setUserName(userDTO.getUserName());
        }
        user.setEmail(userDTO.getEmail());

        return repository.save(user);
    }

//    public void sendMessage(User userDTO, String messageDTO) {
//        Message message = new Message();
//        message.setText(messageDTO);
//        message.setSendDate(LocalDate.now());
//
//        User user = getUserById(userDTO.getId());
//        user.getComments().add(message);
//        repository.save(user);
//
//    }

    public User resetPassword(String email) {
        User user = repository.findUserByUserName(email).get();
        String code = "asdnoasdion";
        //TODO criar algoritmo para enviar codigo de confirmação
        return repository.save(user);
    }

    public User validateCode(String code) {
        //TODO criar codigo de validação de codigo de confirmação
        return null;
    }

    public void addTitle(Long listId, User user, Title title) {
        if (user.isLogged()) {
            TitleList listTitle = titleListService.getTitleListById(listId);
            listTitle.getTitles().add(title);
        }
    }

    public void deleteTitleFromList(Long listId) throws Exception {
        titleListService.deleteTitleList(listId);
    }

}
