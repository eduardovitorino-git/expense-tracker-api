package com.expensetracker.app.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="expense_id")
	private Expense expense;

	public Category() { }
	
	public Category(String name, String description, boolean deleted) {
		super();
		this.name = name;
		this.description = description;
		this.deleted = deleted;
	}
	
	public Category(Long id, String name, String description, Expense expense) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.createdAt = Date.valueOf(LocalDate.now());
		this.expense = expense;
	}
	
	public void addExpense(Expense expense) {
		this.expense = expense;
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

	public Expense getExpense() {
		return expense;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", deleted=" + deleted
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
