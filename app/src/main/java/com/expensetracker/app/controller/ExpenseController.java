package com.expensetracker.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ExpenseController {
	
	private ExpenseService service;
	@SuppressWarnings("unused")
	private ObjectMapper obj;
	
	public ExpenseController(ExpenseService expenseService, ObjectMapper objectMapper) {
		this.service = expenseService;
		this.obj = objectMapper;
	}
	
	@GetMapping("/expenses")
	public List<Expense> getAll() {
		return service.findAll();
	}
}
