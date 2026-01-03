package com.expensetracker.app.service;

import java.util.*;

import com.expensetracker.app.utils.DateRangeParam;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.CategoryDTO;
import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.entity.ExpenseDTO;
import com.expensetracker.app.exception.ExpenseNotFoundException;
import com.expensetracker.app.repository.ExpenseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository repo;
	private final CategoryService categoryService;
	private final ObjectMapper objectMapper;
	
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
		return repo.findAllJoinFetch(Sort.by("amount")).stream()
				.map(this::toDTO)
				.toList();
	}

	@Override
	public List<ExpenseDTO> findAll(String categoryName) {
		Map<String, String> criteria = new HashMap<>();
		criteria.put("categoryName", categoryName);

		return repo.findAllJoinFetch(Sort.by("amount"), criteria).stream()
				.map(this::toDTO)
				.toList();
	}

	@Override
	public List<ExpenseDTO> findAll(DateRangeParam dateRange) {
		Map<String, DateRangeParam> criteria = new HashMap<>();
		criteria.put("DateRangeParam", dateRange);

		return repo.findAllDataRange(Sort.by("amount"), criteria).stream()
				.map(this::toDTO)
				.toList();
	}

	@Override
	public ExpenseDTO findById(Long id) {
		return repo.findByIdJoinFetch(id)
			    .map(this::toDTO)
			    .orElseThrow(() -> new ExpenseNotFoundException("Expense not found. ID: " + id));
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
	
	private List<CategoryDTO> categoryToDTO(List<Category> categories) {
	    if (categories == null) return null;
	    List<CategoryDTO> categoryDtos = new ArrayList<>();
	    
	    for(Category category : categories) {
	    	categoryDtos.add(new CategoryDTO(
	    		category.getId(),
	    		category.getName(),
	    		category.getDescription(),
	    		category.isDeleted(),
	    		category.getCreatedAt(),
	    		category.getUpdatedAt()
		    ));
	    }
	    
	    return categoryDtos;
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
	        categoryToDTO(expense.getListCategory())
	    );
	}
	
	private Expense toEntity(ExpenseDTO dto) {
		Expense expense = new Expense(
				dto.id(),
				dto.amount(),
				dto.description(),
				dto.paymentMethod(),
				dto.recurring(),
				new ArrayList<>()
		);

		if (dto.categories() != null) {
			for (CategoryDTO categoryDto : dto.categories()) {
				Category category = new Category(
						categoryDto.id(),
						categoryDto.name(),
						categoryDto.description(),
						expense
				);
				expense.addCategory(category);
			}
		}

		return expense;
	}
}
