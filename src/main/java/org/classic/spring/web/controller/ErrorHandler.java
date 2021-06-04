package org.classic.spring.web.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(DataAccessException.class)
	public String handleDataAccesException(DataAccessException ex) {
		ex.printStackTrace();
		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccesDeniedException(AccessDeniedException ex) {
		ex.printStackTrace();
		return "denied";
	}
}
