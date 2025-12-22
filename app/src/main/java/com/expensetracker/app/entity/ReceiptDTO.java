package com.expensetracker.app.entity;

import java.sql.Date;

public record ReceiptDTO(
			Long id,
			String receiptImage,
			String ocrExtractedText,
			String merchantName,
			String merchantAddress,
			Date scanDate,
			boolean deleted,
			Date createdAt,
			Date updatedAt
		) {}
