package com.expensetracker.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.expensetracker.app.utils.DateRangeParam;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.app.entity.ExpenseDTO;
import com.expensetracker.app.service.ExpenseService;

@RestController
@RequestMapping("/api")
public class ExpenseController {
	
	private final ExpenseService service;
	
	public ExpenseController(ExpenseService expenseService) {
		this.service = expenseService;
	}
	
	@GetMapping("/expenses")
	public List<ExpenseDTO> getAll() {
		return service.findAll(Sort.by("createdAt").descending());
	}

	@GetMapping("/expenses/category/{category}")
	public List<ExpenseDTO> getAllByAmount(@PathVariable String category) {
		return service.findAll(category);
	}

	@GetMapping("/expenses/date-range")
	public List<ExpenseDTO> getByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
										   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate) {
		DateRangeParam dateRange = new DateRangeParam(initialDate.atStartOfDay(), finalDate.atStartOfDay());
		return service.findAll(dateRange);
	}

	@GetMapping("/expenses/min-amount")
	public ExpenseDTO getMinAmount() {
		return service.findAll(Sort.by("amount").ascending()).getFirst();
	}

	@GetMapping("/expenses/max-amount")
	public ExpenseDTO getMaxAmount() {
		return service.findAll(Sort.by("amount").descending()).getFirst();
	}
	
    @GetMapping("/expenses/{id}")
    public ExpenseDTO getExpense(@PathVariable Long id) {
    	return service.findById(id);
    }

    @PostMapping("/expenses")
    public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO) {
        return service.save(expenseDTO);
    }

	@PutMapping("/expenses")
	public ExpenseDTO updateExpense(@RequestBody ExpenseDTO expenseDTO) {
		return service.save(expenseDTO);
	}

	@DeleteMapping("/expenses/{id}")
	public String delete(@PathVariable Long id) {
		return service.deleteById(id);
	}
}
