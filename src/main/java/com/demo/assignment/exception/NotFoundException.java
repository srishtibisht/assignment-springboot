package com.demo.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	private String errorMessage;

	public NotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
