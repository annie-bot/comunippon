package com.comunippon.ComuNippon.repository;

import com.comunippon.ComuNippon.model.Title;
import com.comunippon.ComuNippon.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
