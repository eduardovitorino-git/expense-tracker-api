package com.expensetracker.app.repository;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.utils.DateRangeParam;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ExpenseRepositoryCustom {
    List<Expense> findAllJoinFetch(Sort sort, Map<String, String> criteria);

    List<Expense> findAllDataRange(Sort sort, Map<String, DateRangeParam> criteria);
}
