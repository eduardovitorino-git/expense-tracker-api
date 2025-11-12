package com.expensetracker.app.service;

import java.util.List;

import com.expensetracker.app.entity.Expense;

public interface ExpenseService {

	List<Expense> findAll();
	
	Expense findById(Long theId);

	Expense save(Expense expense);

	void remove(Expense expense);
	
    void deleteById(Long theId);
}
