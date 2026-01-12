package com.expensetracker.app.utils;

import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.entity.ExpenseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toEntity(ExpenseDTO expenseDto);
    ExpenseDTO toDto(Expense expense);
}
