package com.expensetracker.app.repository;

import com.expensetracker.app.entity.Category;
import com.expensetracker.app.entity.Expense;
import com.expensetracker.app.utils.DateRangeParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExpenseRepositoryCustomImpl implements ExpenseRepositoryCustom {

    EntityManager em;

    public ExpenseRepositoryCustomImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Expense> findAllJoinFetch(Sort sort, Map<String, String> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> query = cb.createQuery(Expense.class);

        Root<Expense> root = query.from(Expense.class);

        root.fetch("listCategory", JoinType.LEFT);
        Join<Expense, Category> categoryJoin = root.join("listCategory", JoinType.LEFT);

        if(criteria.containsKey("categoryName")) {
            String categoryName = criteria.get("categoryName");
            query.where(cb.equal(categoryJoin.get("name"), categoryName));
        }

        if (sort != null && sort.isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : sort) {
                Path<?> path = root.get(sortOrder.getProperty());
                if (sortOrder.isAscending()) {
                    orders.add(cb.asc(path));
                } else {
                    orders.add(cb.desc(path));
                }
            }
            query.orderBy(orders);
        }

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Expense> findAllDataRange(Sort sort, Map<String, DateRangeParam> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Expense> query = cb.createQuery(Expense.class);

        Root<Expense> root = query.from(Expense.class);
        root.fetch("listCategory", JoinType.LEFT);

        if(criteria.containsKey("DateRangeParam")) {
            DateRangeParam dateRangeParam = criteria.get("DateRangeParam");
            Path<LocalDateTime> dateRange = root.get("createdAt");

            Predicate greaterThan = cb.greaterThanOrEqualTo(dateRange, dateRangeParam.getInitialDate());
            Predicate lessThan = cb.lessThan(dateRange, dateRangeParam.getFinalDate());

            query.select(root).where(cb.and(greaterThan, lessThan));
        }

        if (sort != null && sort.isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : sort) {
                Path<?> path = root.get(sortOrder.getProperty());
                if (sortOrder.isAscending()) {
                    orders.add(cb.asc(path));
                } else {
                    orders.add(cb.desc(path));
                }
            }
            query.orderBy(orders);
        }

        return em.createQuery(query).getResultList();
    }
}
