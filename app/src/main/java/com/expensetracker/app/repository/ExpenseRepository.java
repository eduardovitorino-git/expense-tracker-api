package com.expensetracker.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.app.entity.Expense;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, ExpenseRepositoryCustom {

	@Query("SELECT e " +
			"FROM Expense e " +
			"JOIN FETCH e.category " +
			"WHERE e.id = :id " +
			"and e.deleted = false ")
	Optional<Expense> findByIdJoinFetch(@Param("id") Long id);

	@Query("SELECT DISTINCT e " +
			" FROM Expense e " +
			" LEFT JOIN FETCH e.category" +
			" WHERE e.deleted = false")
	List<Expense> findAllJoinFetch(Sort sort);
}
