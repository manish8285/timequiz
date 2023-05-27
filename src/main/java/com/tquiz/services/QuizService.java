package com.tquiz.services;

import java.util.List;
import java.util.Map;

import com.tquiz.dtos.QuizDto;
import com.tquiz.entities.Quiz;

public interface QuizService {
	
	Quiz getQuizById(int id);
	
	Quiz createQuiz(QuizDto quiz);
	
	List<Quiz> getAllQuizes();
	
	List<Quiz> getAllActiveQuizes();
	
	Map<Integer,String> getAnswer(int quizId);
	

}
