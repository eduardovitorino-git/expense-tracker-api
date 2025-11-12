package com.expensetracker.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.app.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
