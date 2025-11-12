package com.expensetracker.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.entity.Receipt;
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
			System.out.println("\n\n\nInitiating application...\n\n\n");
//			addExpense(service);
//			getExpense(service);
//			deleteExpense(service);
//			getReceipt(receiptService);
			deleteReceipt(receiptService);
		};
	}

	
	public void getReceipt(ReceiptService receiptService) {
    	System.out.println("Querying Expense...\n");
        Receipt receipt = receiptService.findById(4L);
        
    	System.out.println("\nPrinting receipt results\n");
        System.out.println(receipt.toString());
        
    	System.out.println("\nPrinting expense\n");
        System.out.println(receipt.getExpense().toString());
	}


    public void getExpense(ExpenseService service) {
    	System.out.println("Querying Expense...\n");
        Expense expense = service.findById(4L);
    	System.out.println("\nPrinting expense results\n");
        System.out.println(expense.toString());
    	System.out.println("\nPrinting receipt\n");
        System.out.println(expense.getReceipt().toString());
    }

    public void addExpense(ExpenseService service) {
    	Expense expense = new Expense();
    	expense.setAmount(100L);
    	expense.setDescription("First expense");
    	
    	Receipt receipt = new Receipt();
    	receipt.setReceiptImage("image.png");
    	receipt.setMerchantName("Padaria");
    	receipt.setOcrExtractedText("100 - Pão Francês");
    	
    	expense.setReceipt(receipt);
    	
        Expense dbExpense = service.save(expense);
        System.out.println(dbExpense.toString());
    }


    public void deleteExpense(ExpenseService service) {
    	System.out.println("Querying Expense...\n");
        Expense expense = service.findById(3L);
    	System.out.println("Deleting Expense...\n");
        service.remove(expense);
    	System.out.println("\nExpense deleted\n");
    }
    
    public void deleteReceipt(ReceiptService service) {
    	System.out.println("Querying Expense...\n");
        Receipt receipt = service.findById(5L);
        
    	System.out.println("Deleting Expense...\n");
        service.remove(receipt);
        
    	System.out.println("\nExpense deleted\n");
    }
    
    public void deleteExpenseById(ExpenseService service) {
    	System.out.println("Deleting Expense...\n");
        service.deleteById(2L);
    	System.out.println("\nExpense deleted\n");
    }
}
