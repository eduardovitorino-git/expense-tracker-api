package com.expensetracker.app.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="deleted")
	private boolean deleted;

	@Column(name="createdAt")
	private Date createdAt;
	
	@Column(name="updatedAt")
	private Date updatedAt;

	@OneToMany(mappedBy="category",
			fetch=FetchType.LAZY,
			cascade={CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Expense> expenses;

	public Category() { }
	
	public Category(String name, String description, boolean deleted) {
		super();
		this.name = name;
		this.description = description;
		this.deleted = deleted;
	}
	
	public Category(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = Date.valueOf(LocalDate.now());
	}
	
	public void addExpense(Expense expense) {
		if(expenses == null) expenses = new ArrayList<>();
		expense.setCategory(this);
		expenses.add(expense);
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", deleted=" + deleted
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
