package com.expensetracker.app.repository;

import com.expensetracker.app.entity.Expense;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ExpenseRepositoryCustom {
    List<Expense> findAllJoinFetch(Sort sort, Map<String, Long> criteria);
}
