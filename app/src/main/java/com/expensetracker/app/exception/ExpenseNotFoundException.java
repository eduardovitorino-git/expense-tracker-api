package com.expensetracker.app.exception;

public class ExpenseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExpenseNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExpenseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpenseNotFoundException(String message) {
		super(message);
	}
	
}