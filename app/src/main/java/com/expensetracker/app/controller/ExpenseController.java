package com.expensetracker.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller
//@RequestMapping("/api")
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
	
	
    @GetMapping("/Expenses/{ExpenseId}")
    public Expense getExpense(@PathVariable Long ExpenseId) {

        Expense theExpense = service.findById(ExpenseId);

        if (theExpense == null) {
            throw new RuntimeException("Expense id not found - " + ExpenseId);
        }

        return theExpense;
    }


    @PostMapping("/Expenses")
    public Expense addExpense(@RequestBody Expense theExpense) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theExpense.setId(null);

        Expense dbExpense = service.save(theExpense);

        return dbExpense;
    }

    // add mapping for PUT /Expenses - update existing Expense

    @PutMapping("/Expenses")
    public Expense updateExpense(@RequestBody Expense theExpense) {

        Expense dbExpense = service.save(theExpense);

        return dbExpense;
    }

    // add mapping for DELETE /Expenses/{ExpenseId} - delete Expense

    @DeleteMapping("/Expenses/{ExpenseId}")
    public String deleteExpense(@PathVariable Long ExpenseId) {

        Expense tempExpense = service.findById(ExpenseId);

        // throw exception if null

        if (tempExpense == null) {
            throw new RuntimeException("Expense id not found - " + ExpenseId);
        }

        service.deleteById(ExpenseId);

        return "Deleted Expense id - " + ExpenseId;
    }
	
	
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
