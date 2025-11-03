package com.expensetracker.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseRepository repo;
	
	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.repo = expenseRepository;
	}
	
	@Override
	public List<Expense> findAll() {
		return repo.findAll();
	}

}
