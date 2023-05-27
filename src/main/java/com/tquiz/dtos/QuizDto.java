package com.tquiz.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.Data;


//this dto class will be used in case we don't want to expose our entity class object to end user
@Data
public class QuizDto {
	private int id;
	
	private String question;
	private List<String> options = new ArrayList<String>();
	private int rightAnswer;
	//here the start date and end date will be user's defined format
	private String startDate;
	private String endDate;
	
	@Transient
	private String status;

}
