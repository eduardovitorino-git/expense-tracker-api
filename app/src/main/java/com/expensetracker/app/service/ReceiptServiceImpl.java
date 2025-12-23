package com.expensetracker.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Receipt;
import com.expensetracker.app.repository.ReceiptRepository;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	private ReceiptRepository repo;
	
	public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
		this.repo = receiptRepository;
	}
	
	@Override
	public Receipt findById(Long theId) {
	    Optional<Receipt> result = repo.findById(theId);
	    Receipt receipt = null;
	    if (result.isPresent()) {
	    	receipt = result.get();
	    }
	    else {
	        throw new RuntimeException("Did not find employee id - " + theId);
	    }
	    return receipt;
	}

	@Override
	public void remove(Receipt receipt) {
		receipt.removeExpense();
		repo.delete(receipt);
	}
}
