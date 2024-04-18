package com.comunippon.ComuNippon.repository;

import com.comunippon.ComuNippon.model.Message;
import com.comunippon.ComuNippon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUser(User user);

}
