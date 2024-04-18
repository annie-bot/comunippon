package com.comunippon.ComuNippon.repository;

import com.comunippon.ComuNippon.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}
