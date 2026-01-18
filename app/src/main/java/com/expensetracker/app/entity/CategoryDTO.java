package com.expensetracker.app.entity;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.List;

public record CategoryDTO(
			Long id,

			@NotBlank(message = "Name is required")
			String name,

			String description,
			boolean deleted,
			Date createdAt,
			Date updatedAt,
			List<Expense> expenses
		) {}
