package com.expensetracker.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseRepository repo;
	private CategoryService categoryService;
	
	public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryService categoryService) {
		this.repo = expenseRepository;
		this.categoryService = categoryService;
	}
	
	@Override
	public List<Expense> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Expense findById(Long theId) {
	    Optional<Expense> result = repo.findById(theId);
	    Expense theEmployee = null;
	    if (result.isPresent()) {
	        theEmployee = result.get();
	    }
	    else {
	        throw new RuntimeException("Did not find employee id - " + theId);
	    }
	    return theEmployee;
	}
	
	@Override
	public Expense findByIdJoinFetch(Long theId) {
		Optional<Expense> result = repo.findByIdJoinFetch(theId);
	    Expense theEmployee = null;
	    if (result.isPresent()) {
	        theEmployee = result.get();
	    }
	    else {
	        throw new RuntimeException("Did not find employee id - " + theId);
	    }
	    return theEmployee;
	}
	
	@Override
	public Expense save(Expense expense) {
	    return repo.save(expense);
	}
	
	@Override
	public void remove(Expense expense) {
		
		// Break category relationship
		for(Category category : expense.getListCategory()) {
			category.setExpense(null);
		}
		
		repo.delete(expense);
	}
	
	@Override
	public void deleteById(Long theId) {
		repo.deleteById(theId);
	}
	
	@Override
	public List<Category> findCategoriesByExpenseId(Long theId) {
		return categoryService.findAllByExpenseId(theId);
	}
	
}
