package com.expensetracker.app.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public record ExpenseDTO(
		 Long id,

		 @NotNull(message = "Amount is required")
		 @Positive(message = "Amount must be more than zero")
		 BigDecimal amount,

		 @NotBlank(message = "Description é obrigatória")
		 String description,

		 String location,
		 String paymentMethod,
		 boolean deleted,
		 boolean recurring,
		 LocalDateTime createdAt,
		 Date updatedAt,
		 CategoryDTO category
	) {

}
