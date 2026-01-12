package com.expensetracker.app;

import com.expensetracker.app.service.ExpenseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpenseTrackerApplicationTests {

	private static ExpenseService expenseService;

	@BeforeAll
	static void contextLoads(ExpenseService serviceImpl) {
		expenseService = serviceImpl;
	}

	@Test
	void testFindByDateRange() {
	}
}
