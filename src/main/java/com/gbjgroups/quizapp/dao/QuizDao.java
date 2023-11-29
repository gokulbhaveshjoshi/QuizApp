package com.gbjgroups.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbjgroups.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

    
} 
