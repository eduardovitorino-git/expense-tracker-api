package com.expensetracker.app.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public record ExpenseDTO(
		 Long id,
		 BigDecimal amount,
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
