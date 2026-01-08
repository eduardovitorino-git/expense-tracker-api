package com.expensetracker.app.controller;

import java.util.List;
import java.util.Map;

import com.expensetracker.app.utils.DateRangeParam;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<ExpenseDTO> getByDateRange(@RequestBody DateRangeParam dateRange) {
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

	@PatchMapping("/expenses/{id}")
	public ExpenseDTO patch(@PathVariable Long id, @RequestBody Map<String, Object> patchPayload) {
		return service.update(id, patchPayload);
	}
	
	@DeleteMapping("/expenses/{id}")
	public String delete(@PathVariable Long id) {
		return service.deleteById(id);
	}
}
