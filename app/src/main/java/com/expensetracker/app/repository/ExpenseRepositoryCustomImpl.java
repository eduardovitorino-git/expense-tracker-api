package com.expensetracker.app.repository;

import com.expensetracker.app.entity.Expense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpenseRepositoryCustomImpl implements ExpenseRepositoryCustom {

    EntityManager em;

    public ExpenseRepositoryCustomImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Expense> findAllJoinFetch(Sort sort, Map<String, Long> criteria) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> query = cb.createQuery(Expense.class);

        Root<Expense> root = query.from(Expense.class);
        root.fetch("listCategory", JoinType.LEFT);

        if(criteria.containsKey("amount")) {
            Path<Long> amount = root.get("amount");
            query.select(root).where(cb.ge(amount, criteria.get("amount")));
        }

        return em.createQuery(query).getResultList();
    }
}
