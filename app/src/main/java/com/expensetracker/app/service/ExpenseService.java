package com.expensetracker.app.service;

import java.util.List;
import java.util.Map;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.ExpenseDTO;

public interface ExpenseService {

	List<ExpenseDTO> findAll();
	
	ExpenseDTO findById(Long theId);
	
	ExpenseDTO findByIdJoinFetch(Long theId);

	ExpenseDTO save(ExpenseDTO expense);
	
	ExpenseDTO update(Long id, Map<String, Object> patchPayload);

    String deleteById(Long theId);
    
    public List<Category> findCategoriesByExpenseId(Long theId);
}
