package com.expensetracker.app.service;

import com.expensetracker.app.entity.Receipt;

public interface ReceiptService {
	
	Receipt findById(Long theId);
	
}
