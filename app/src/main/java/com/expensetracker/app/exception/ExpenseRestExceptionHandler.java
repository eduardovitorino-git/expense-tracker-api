package com.expensetracker.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ControllerAdvice is global exception handler
 */
@ControllerAdvice
public class ExpenseRestExceptionHandler {

	/**
	 *  Define a method to handle exceptions.
	 *  This one handle my custom exception for expense not found
	 */
	@ExceptionHandler
	public ResponseEntity<ExpenseErrorResponse> error(ExpenseNotFoundException exec) {
		ExpenseErrorResponse error = new ExpenseErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exec.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		/**
		 * Exception handlers returns a ResponseEntity 
		 * that works as a wrapper for HTTP response object.
		 */
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception handler for all other types of exception 
	 * for the expense rest controller
	 */
	@ExceptionHandler
	public ResponseEntity<ExpenseErrorResponse> error(Exception exec) {
		ExpenseErrorResponse error = new ExpenseErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exec.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		/**
		 * Exception handlers returns a ResponseEntity 
		 * that works as a wrapper for HTTP response object.
		 */
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
