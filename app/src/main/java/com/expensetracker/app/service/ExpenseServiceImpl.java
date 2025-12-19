package com.expensetracker.app.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.exception.ExpenseNotFoundException;
import com.expensetracker.app.repository.ExpenseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private ExpenseRepository repo;
	private CategoryService categoryService;
	private ObjectMapper objectMapper;
	
	public ExpenseServiceImpl(ExpenseRepository expenseRepository, 
			CategoryService categoryService, ObjectMapper objectMapper) {
		this.repo = expenseRepository;
		this.categoryService = categoryService;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public Expense save(Expense player) {
		return repo.save(player);
	}

	@Override
	public List<Expense> findAll() {
		return repo.findAll();
	}

	@Override
	public Expense findById(Long id) {
		Optional<Expense> dbPlayer = repo.findById(id);
		if(!dbPlayer.isPresent()) throw new ExpenseNotFoundException("Expense not found. ID: " + id);
		return dbPlayer.get();
	}
	
	@Override
	public Expense findByIdJoinFetch(Long theId) {
		Optional<Expense> result = repo.findByIdJoinFetch(theId);
	    Expense theEmployee = null;
	    if (result.isPresent()) {
	        theEmployee = result.get();
	    }
	    else {
	        throw new RuntimeException("Did not find employee id - " + theId);
	    }
	    return theEmployee;
	}
	
	@Override
	public List<Category> findCategoriesByExpenseId(Long theId) {
		return categoryService.findAllByExpenseId(theId);
	}
	
	@Override
	public Expense update(Long id, Map<String, Object> patchPayload) {
		Optional<Expense> dbPlayer = repo.findById(id);
		if(!dbPlayer.isPresent()) throw new ExpenseNotFoundException("Expense not found. ID: " + id);
		if(patchPayload.containsKey("id")) throw new RuntimeException("Not allowed to change the ID.");
		Expense patchedPlayer = apply(patchPayload, dbPlayer.get());
		return repo.save(patchedPlayer);
	}

	@Override
	public String delete(Expense player) {
		repo.delete(player);
		return "Sucesso";
	}
	
	@Override
	public String deleteById(Long id) {
		Expense player = new Expense();
		Optional<Expense> dbPlayer = repo.findById(id);
		if(!dbPlayer.isPresent()) throw new ExpenseNotFoundException("Expense not found. ID: " + player.getId());
		player = dbPlayer.get();
		repo.delete(player);
		return "Sucesso";
	}
	
	private Expense apply(Map<String, Object> patchPayload, Expense player) {
		// Convert the class to JSON
		ObjectNode expenseNode = this.objectMapper.convertValue(player, ObjectNode.class);
		// Convert the Map to JSON
		ObjectNode patchNode = this.objectMapper.convertValue(patchPayload, ObjectNode.class);
		// Apply the differences
		expenseNode.setAll(patchNode);
		// Convert back to Expense object and return it
		return this.objectMapper.convertValue(expenseNode, Expense.class);
		
	}
}
