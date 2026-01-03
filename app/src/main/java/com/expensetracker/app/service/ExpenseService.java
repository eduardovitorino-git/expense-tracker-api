package com.expensetracker.app.service;

import java.util.List;
import java.util.Map;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.ExpenseDTO;
import com.expensetracker.app.utils.DateRangeParam;

public interface ExpenseService {

	List<ExpenseDTO> findAll();

	List<ExpenseDTO> findAll(String amount);

	List<ExpenseDTO> findAll(DateRangeParam dateRange);

	ExpenseDTO findById(Long theId);
	
	ExpenseDTO save(ExpenseDTO expense);
	
	ExpenseDTO update(Long id, Map<String, Object> patchPayload);

    String deleteById(Long theId);
    
    public List<Category> findCategoriesByExpenseId(Long theId);
}
