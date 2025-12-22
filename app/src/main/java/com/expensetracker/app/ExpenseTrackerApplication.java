package com.expensetracker.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.expensetracker.app.service.ExpenseService;
import com.expensetracker.app.service.ReceiptService;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ExpenseService service, ReceiptService receiptService) {
		return runner -> {
			System.out.println("\n\nInitiating application...\n\n");
		};
	}
}
