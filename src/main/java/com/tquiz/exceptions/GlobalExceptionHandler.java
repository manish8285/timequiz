package com.tquiz.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// this method will handle all the run time exception and user will get neat response from apis
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> methodArgumentNotValidException(RuntimeException ex){
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
