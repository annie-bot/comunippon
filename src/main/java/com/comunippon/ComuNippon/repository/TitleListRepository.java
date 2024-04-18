package com.comunippon.ComuNippon.repository;

import com.comunippon.ComuNippon.model.TitleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleListRepository extends JpaRepository<TitleList, Long> {
}
