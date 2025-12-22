package com.expensetracker.app.entity;

import java.sql.Date;

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
		 ReceiptDTO receipt
	) {

}
