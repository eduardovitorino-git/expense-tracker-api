package com.expensetracker.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.app.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	@Query("SELECT e "
			+ "	FROM Expense e "
			+ " JOIN FETCH e.listCategory"
			+ " WHERE e.id = ?1")
	Optional<Expense> findByIdJoinFetch(Long theId);
}
