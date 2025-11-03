package com.expensetracker.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.app.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, UUID>{

}
