package com.expensetracker.app.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="expense")
public class Expense {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="location")
	private String location;

	@Column(name="merchant")
	private String merchant;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="recurring")
	private boolean recurring;
	
	@Column(name="createdAt")
	private Date createdAt;
	
	@Column(name="updatedAt")
	private Date updatedAt;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receipt_id")
	private Receipt receipt;
	
	@OneToMany(mappedBy="expense", 
			   cascade={CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Category> listCategory;
	
	public Expense() { }

	public Expense(Long amount, String description, String location,
			String merchant, String paymentMethod, boolean deleted, boolean recurring, Date createdAt, Date updatedAt) {
		this.amount = amount;
		this.description = description;
		this.location = location;
		this.merchant = merchant;
		this.paymentMethod = paymentMethod;
		this.deleted = deleted;
		this.recurring = recurring;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
	
	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}
	
	public void addCategory(Category category) {
		if(listCategory == null) listCategory = new ArrayList<>();
		listCategory.add(category);
		category.setExpense(this);
	}

	public void setListCategory(List<Category> category) {
		this.listCategory = category;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount
				+ ", description=" + description + ", location=" + location + ", merchant=" + merchant
				+ ", paymentMethod=" + paymentMethod + ", deleted=" + deleted + ", recurring=" + recurring
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
