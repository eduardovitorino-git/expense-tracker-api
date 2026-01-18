package com.expensetracker.app.service;

import java.util.*;

import com.expensetracker.app.utils.DateRangeParam;
import com.expensetracker.app.utils.ExpenseMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.entity.ExpenseDTO;
import com.expensetracker.app.exception.ExpenseNotFoundException;
import com.expensetracker.app.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository repo;
	private final ExpenseMapper mapper;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository,
			CategoryService categoryService,
			ExpenseMapper mapper) {
		this.repo = expenseRepository;
		this.mapper = mapper;
	}
	
	@Override
	public ExpenseDTO save(ExpenseDTO expenseDTO) {
		Expense expense = mapper.toEntity(expenseDTO);
		return mapper.toDto(repo.save(expense));
	}
	
	@Override
	public List<ExpenseDTO> findAll(Sort sort) {
		return repo.findAllJoinFetch(sort).stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	public List<ExpenseDTO> findAll(String categoryName) {
		Map<String, String> criteria = new HashMap<>();
		criteria.put("categoryName", categoryName);

		return repo.findAllJoinFetch(Sort.by("amount"), criteria).stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	public List<ExpenseDTO> findAll(DateRangeParam dateRange) {
		Map<String, DateRangeParam> criteria = new HashMap<>();
		criteria.put("DateRangeParam", dateRange);

		return repo.findAllDataRange(Sort.by("amount"), criteria).stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	public ExpenseDTO findById(Long id) {
		return repo.findByIdJoinFetch(id)
			    .map(mapper::toDto)
			    .orElseThrow(() -> new ExpenseNotFoundException("Expense not found. ID: " + id));
	}
	
	@Override
	public String deleteById(Long id) {
		Optional<Expense> dbExpense = repo.findById(id);
		if(dbExpense.isEmpty()) throw new ExpenseNotFoundException("Expense not found. ID: " + id);
		Expense expense = dbExpense.get();
		expense.delete();
		repo.save(expense);
		return "Sucesso";
	}
}
