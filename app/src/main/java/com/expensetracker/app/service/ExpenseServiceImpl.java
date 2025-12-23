package com.expensetracker.app.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.entity.ExpenseDTO;
import com.expensetracker.app.entity.Receipt;
import com.expensetracker.app.entity.ReceiptDTO;
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
	public ExpenseDTO save(ExpenseDTO expenseDTO) {
		Expense expense = toEntity(expenseDTO);
		return toDTO(repo.save(expense));
	}
	
	@Override
	public List<ExpenseDTO> findAll() {
		return repo.findAll().stream()
				.map(this::toDTO)
				.toList();
	}

	@Override
	public ExpenseDTO findById(Long id) {
		return repo.findById(id)
			    .map(this::toDTO)
			    .orElseThrow(() -> new ExpenseNotFoundException("Expense not found. ID: " + id));
	}
	
	@Override
	public ExpenseDTO findByIdJoinFetch(Long theId) {
		Optional<Expense> result = repo.findByIdJoinFetch(theId);
	    Expense expense = null;
	    if (result.isPresent()) expense = result.get();
	    else throw new RuntimeException("Did not find employee id - " + theId);
	    return toDTO(expense);
	}
	
	@Override
	public List<Category> findCategoriesByExpenseId(Long theId) {
		return categoryService.findAllByExpenseId(theId);
	}
	
	@Override
	public ExpenseDTO update(Long id, Map<String, Object> patchPayload) {
		Optional<Expense> expenseDb = repo.findById(id);
		if(!expenseDb.isPresent()) throw new ExpenseNotFoundException("Expense not found. ID: " + id);
		if(patchPayload.containsKey("id")) throw new RuntimeException("Not allowed to change the ID.");
		ExpenseDTO patchedExpenseDTO = apply(patchPayload, expenseDb.get());
		Expense patchedExpense = toEntity(patchedExpenseDTO);
		return toDTO(repo.save(patchedExpense));
	}

	@Override
	public String deleteById(Long id) {
		Expense expense = new Expense();
		Optional<Expense> dbExpense = repo.findById(id);
		if(!dbExpense.isPresent()) throw new ExpenseNotFoundException("Expense not found. ID: " + id);
		expense = dbExpense.get();
		expense.delete();
		repo.save(expense);
		return "Sucesso";
	}
	
	private ExpenseDTO apply(Map<String, Object> patchPayload, Expense player) {
		// Convert the class to JSON
		ObjectNode expenseNode = this.objectMapper.convertValue(player, ObjectNode.class);
		// Convert the Map to JSON
		ObjectNode patchNode = this.objectMapper.convertValue(patchPayload, ObjectNode.class);
		// Apply the differences
		expenseNode.setAll(patchNode);
		// Convert back to Expense object and return it
		return this.objectMapper.convertValue(expenseNode, ExpenseDTO.class);
		
	}
	
	private ReceiptDTO toDTO(Receipt receipt) {
	    if (receipt == null) return null;
	    
	    return new ReceiptDTO(
	        receipt.getId(),
	        receipt.getReceiptImage(),
	        receipt.getOcrExtractedText(),
	        receipt.getMerchantName(),
	        receipt.getMerchantAddress(),
	        receipt.getScanDate(),
	        receipt.isDeleted(),
	        receipt.getCreatedAt(),
	        receipt.getUpdatedAt()
	    );
	}

	private ExpenseDTO toDTO(Expense expense) {
	    return new ExpenseDTO(
	        expense.getId(),
	        expense.getAmount(),
	        expense.getDescription(),
	        expense.getLocation(),
	        expense.getMerchant(),
	        expense.getPaymentMethod(),
	        expense.isDeleted(),
	        expense.isRecurring(),
	        expense.getCreatedAt(),
	        expense.getUpdatedAt(),
	        toDTO(expense.getReceipt())
	    );
	}
	
	private Expense toEntity(ExpenseDTO dto) {
	    Receipt receipt = null;
	    
	    if (dto.receipt() != null) {
	        ReceiptDTO r = dto.receipt();
	        receipt = new Receipt(
	            r.id(),
	            r.receiptImage(),
	            r.ocrExtractedText(),
	            r.merchantName(),
	            r.merchantAddress(),
	            r.scanDate(),
	            r.deleted(),
	            r.createdAt(),
	            r.updatedAt()
	        );
	    }
	    
	    return new Expense(
	        dto.id(),
	        dto.amount(),
	        dto.description(),
	        dto.location(),
	        dto.merchant(),
	        dto.paymentMethod(),
	        dto.deleted(),
	        dto.recurring(),
	        dto.createdAt(),
	        dto.updatedAt(),
	        receipt
	    );
	}
}
