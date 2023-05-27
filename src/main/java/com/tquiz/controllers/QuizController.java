package com.tquiz.controllers;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tquiz.dtos.QuizDto;
import com.tquiz.entities.Quiz;
import com.tquiz.services.QuizService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
public class QuizController {
	
	// for rate limit bucket variable
	private final Bucket bucket;
	
	// rate limit through bucket4j
	public QuizController() {
		Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
	}
	
	@Autowired
	private QuizService quizService;
	
	//this is only for testing purpose
	@GetMapping("/")
	public ResponseEntity<String> home(){
		return ResponseEntity.status(HttpStatus.OK).body("Quiz Application");
	}
	
	//this end point will create new quiz
	@PostMapping("/quizzes")
	public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quiz){
		if (bucket.tryConsume(1)) {
			return ResponseEntity.status(HttpStatus.CREATED).body( this.quizService.createQuiz(quiz));
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	}
	
	// this end point will show right answer of the quiz
	@GetMapping("/quizzes/{id}/result")
	public ResponseEntity<Map<Integer,String>> getQuizResult(@PathVariable Integer id){
		if (bucket.tryConsume(1)) {
			return ResponseEntity.status(HttpStatus.OK).body( this.quizService.getAnswer(id));
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	}
	
	//this end point will show all the listed quizzes
	@GetMapping("/quizzes/all")
	public ResponseEntity<List<Quiz>> getAllQuizes(){
		if (bucket.tryConsume(1)) {
			return ResponseEntity.status(HttpStatus.OK).body( this.quizService.getAllQuizes());
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	}
	
	//this end point will show all the active quizzess
	@GetMapping("/quizzes/active")
	public ResponseEntity<List<Quiz>> getAllActiveQuizes(){
		if (bucket.tryConsume(1)) {
		return ResponseEntity.status(HttpStatus.OK).body( this.quizService.getAllActiveQuizes());
		}
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
	}
	
	
	
	

}
