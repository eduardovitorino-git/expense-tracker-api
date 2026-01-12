package com.expensetracker.app.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="expense")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="location")
	private String location;

	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="recurring")
	private boolean recurring;
	
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	private Date updatedAt;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="receipt_id")
	private Receipt receipt;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="category_id")
	private Category category;
	
	public Expense() { }

	public Expense(BigDecimal amount, String description, String location,
			String paymentMethod, boolean deleted, boolean recurring, LocalDateTime createdAt, Date updatedAt) {
		this.amount = amount;
		this.description = description;
		this.location = location;
		this.paymentMethod = paymentMethod;
		this.deleted = deleted;
		this.recurring = recurring;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Expense(Long id, BigDecimal amount, String description, String location,
			String paymentMethod, boolean deleted, boolean recurring, LocalDateTime createdAt, Date updatedAt, Receipt receipt) {
		this.amount = amount;
		this.description = description;
		this.location = location;
		this.paymentMethod = paymentMethod;
		this.deleted = deleted;
		this.recurring = recurring;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Expense(Long id, BigDecimal amount, String description, String paymentMethod, boolean recurring) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.paymentMethod = paymentMethod;
		this.recurring = recurring;
		this.createdAt = LocalDateTime.now();
	}
	
	public void delete() {
		this.deleted = true;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public Category getCategory() {
		return category;
	}
	
	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount
				+ ", description=" + description + ", location=" + location
				+ ", paymentMethod=" + paymentMethod + ", deleted=" + deleted + ", recurring=" + recurring
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
