package com.expensetracker.app.entity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="expense")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id")
	private UUID id;
	@Column(name="amount")
	private Integer amount;
	@Column(name="currency")
	private String currency;
	@Column(name="description")
	private String description;
	@Column(name="categoryId")
	private String categoryId;
	@Column(name="date")
	private Date date;
	@Column(name="paymentMethod")
	private String paymentMethod;
	@Column(name="merchant")
	private String merchant;
	@Column(name="location")
	private String location;
	@Column(name="recurring")
	private boolean recurring;
	@Column(name="createdAt")
	private Date createdAt;
	@Column(name="updatedAt")
	private Date updatedAt;
	@Column(name="deleted")
	private boolean deleted;
	
	private List<String> tags;
	
	public Expense() { }
	
	public Expense(Integer amount, String currency, String description, String categoryId, Date date,
			String paymentMethod, String merchant, String location, List<String> tags, boolean recurring, String notes,
			Date createdAt, Date updatedAt, boolean deleted) {
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.categoryId = categoryId;
		this.date = date;
		this.paymentMethod = paymentMethod;
		this.merchant = merchant;
		this.location = location;
		this.tags = tags;
		this.recurring = recurring;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deleted = deleted;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
