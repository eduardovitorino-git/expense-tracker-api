package com.expensetracker.app.service;

import java.util.List;

import com.expensetracker.app.entity.Expense;

public interface ExpenseService {

	List<Expense> findAll();
}
