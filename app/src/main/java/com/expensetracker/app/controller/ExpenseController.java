package com.expensetracker.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.expensetracker.app.entity.Expense;

@Controller
//@RequestMapping("/api")
public class ExpenseController {
	
//	private ExpenseService service;
//	@SuppressWarnings("unused")
//	private ObjectMapper obj;
//	
//	public ExpenseController(ExpenseService expenseService, ObjectMapper objectMapper) {
//		this.service = expenseService;
//		this.obj = objectMapper;
//	}
//	
//	@GetMapping("/expenses")
//	public List<Expense> getAll() {
//		return service.findAll();
//	}
	
//	@GetMapping("/hello")
//	public String getHello(Model theModel) {
//		theModel.addAttribute("theDate", java.time.LocalDateTime.now());
//		return "hello";
//	}
	
	// Model is for sharing data between controller and view
	@GetMapping("/expenseForm")
	public String showForm(Model model) {
		model.addAttribute("expense", new Expense());
		return "expense-form";
	}
	
//	@GetMapping("/processForm")
//	public String processForm() {
//		return "hello";
//	}
	
	// HttpServletRequest is a object for the HTML elements
	@PostMapping("/processForm")
	public String processForm(@ModelAttribute("expense") Expense expense) {
		return "expense-confirmation";
	}
}
