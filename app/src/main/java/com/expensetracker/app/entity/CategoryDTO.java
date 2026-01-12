package com.expensetracker.app.entity;

import java.sql.Date;
import java.util.List;

public record CategoryDTO(
			Long id,
			String name,
			String description,
			boolean deleted,
			Date createdAt,
			Date updatedAt,
			List<Expense> expenses
		) {}
