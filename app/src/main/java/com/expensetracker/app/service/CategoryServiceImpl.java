package com.expensetracker.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository repo;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.repo = categoryRepository;
	}

//	@Override
//	public List<Category> findAllByExpenseId(Long expenseId) {
//		return repo.findAllByExpenseId(expenseId);
//	}
	
}
