package com.expensetracker.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.app.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT category FROM Category category WHERE category.expense.id = ?1")
	List<Category> findAllByExpenseId(Long expenseId);
}
