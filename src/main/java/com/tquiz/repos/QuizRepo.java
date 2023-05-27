package com.tquiz.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tquiz.entities.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
