package com.expensetracker.app.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="receipt_id")
	private Receipt receipt;
	
	@OneToMany(mappedBy="expense", 
			   fetch=FetchType.LAZY,
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
	
	public Expense(Long id, Long amount, String description, String location,
			String merchant, String paymentMethod, boolean deleted, boolean recurring, Date createdAt, Date updatedAt, Receipt receipt) {
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
	
	public Expense(Long id, Long amount, String description, String paymentMethod, boolean recurring, List<Category> listCategory) {
		this.amount = amount;
		this.description = description;
		this.paymentMethod = paymentMethod;
		this.recurring = recurring;
		this.createdAt = Date.valueOf(LocalDate.now());
		this.listCategory = listCategory;
	}
	
	public void addCategory(Category category) {
		if(listCategory == null) listCategory = new ArrayList<>();
		category.addExpense(this);
		listCategory.add(category);
	}
	
	public void delete() {
		this.deleted = true;
	}

	public void setListCategory(List<Category> category) {
		this.listCategory = category;
	}
	
	public Long getId() {
		return id;
	}

	public Long getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getMerchant() {
		return merchant;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}
	
	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount
				+ ", description=" + description + ", location=" + location + ", merchant=" + merchant
				+ ", paymentMethod=" + paymentMethod + ", deleted=" + deleted + ", recurring=" + recurring
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
