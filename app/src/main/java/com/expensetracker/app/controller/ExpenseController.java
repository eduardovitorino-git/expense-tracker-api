package com.expensetracker.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
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
	
    @GetMapping("/expenses/{id}")
    public Expense getExpense(@PathVariable Long id) {
    	return service.findById(id);
    }
    
    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense theExpense) {
        theExpense.setId(null);
        return service.save(theExpense);
    }

    @PutMapping("/expenses")
    public Expense updateExpense(@RequestBody Expense theExpense) {
    	return service.save(theExpense);
    }
	
	@PatchMapping("/expenses/{id}")
	public Expense patch(@PathVariable Long id, @RequestBody Map<String, Object> patchPayload) {
		return service.update(id, patchPayload);
	}
	
	@DeleteMapping("/expenses/{id}")
	public String delete(@PathVariable Long id) {
		return service.deleteById(id);
	}
}
