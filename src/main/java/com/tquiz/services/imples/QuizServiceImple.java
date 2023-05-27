package com.tquiz.services.imples;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tquiz.dtos.QuizDto;
import com.tquiz.entities.Quiz;
import com.tquiz.repos.QuizRepo;
import com.tquiz.services.QuizService;

@Service
public class QuizServiceImple implements QuizService {
	
	@Autowired
	private QuizRepo quizRepo;

	@Override
	public Quiz getQuizById(int id) {
		
		return this.quizRepo.findById(id).orElseThrow(()->new RuntimeException("resource not found quiz"));
	}

	// quiz will be saved to db after typecasting into quiz entity
	// and date will be stored in single defined format
	@Override
	public Quiz createQuiz(QuizDto quizDto) {
		// converting dto object entity object
		Quiz quiz = new Quiz();
		quiz.setQuestion(quizDto.getQuestion());
		quiz.setRightAnswer(quizDto.getRightAnswer());
		quiz.setOptions(quizDto.getOptions());
		
		//converting date to right format
		try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		quiz.setStartDate(sdf.parse(quizDto.getStartDate()));
		quiz.setEndDate(sdf.parse(quizDto.getEndDate()));
		//quiz.setStartDate(sdf.parse(null));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.quizRepo.save(quiz);
	}

	
	//this method will fetch all the quizes
	@Override
	public List<Quiz> getAllQuizes() {
		return this.quizRepo.findAll();
	}

	//this method will return all the active quizes on the basis of status property
	@Override
	public List<Quiz> getAllActiveQuizes() {
		
		return this.getAllQuizes().stream().filter(obj->obj.getStatus().equals("active")).collect(Collectors.toList());
	}

	//implementation of cache
	//answer will be fetch database only if it is not available in cache
	@Cacheable(cacheNames = "quizzes",key="#quizId")
	@Override
	public Map<Integer, String> getAnswer(int quizId) {
		Quiz quiz = this.getQuizById(quizId);
		Map<Integer,String> ans = new HashMap<Integer,String>();
		
		ans.put(quiz.getRightAnswer(),quiz.getOptions().get(quiz.getRightAnswer()));
		return ans;
	}

}
