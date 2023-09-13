package com.example.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<MyErrorDetails> userNotFoundExceptionHandler(UserException ue, WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();
		err.setTime(LocalDateTime.now());
		err.setMessage("User exception");

		return new ResponseEntity<MyErrorDetails>(err, HttpStatusCode.valueOf(400));

	}

}
