package com.expensetracker.app.entity;

import java.sql.Date;
import java.util.List;

public record ExpenseDTO(
		 Long id,
		 Long amount,
		 String description,
		 String location,
		 String merchant,
		 String paymentMethod,
		 boolean deleted,
		 boolean recurring,
		 Date createdAt,
		 Date updatedAt,
		 List<CategoryDTO> categories
	) {

}
