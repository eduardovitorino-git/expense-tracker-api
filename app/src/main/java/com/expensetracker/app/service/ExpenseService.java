package com.expensetracker.app.service;

import java.util.List;
import java.util.Map;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.Expense;

public interface ExpenseService {

	List<Expense> findAll();
	
	Expense findById(Long theId);
	
	Expense findByIdJoinFetch(Long theId);

	Expense save(Expense expense);
	
	Expense update(Long id, Map<String, Object> patchPayload);

	String delete(Expense expense);
	
    String deleteById(Long theId);
    
    public List<Category> findCategoriesByExpenseId(Long theId);
}
