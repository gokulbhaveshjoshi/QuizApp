package com.gbjgroups.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gbjgroups.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM QUESTION Q WHERE Q.category =:category ORDER BY RANDOM() LIMIT  :numQ", nativeQuery = true)
    List<Question> findRandomByCategory(String category, int numQ);
} 
