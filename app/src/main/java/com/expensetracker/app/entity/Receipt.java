package com.expensetracker.app.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="receipt")
public class Receipt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="receipt_image")
	private String receiptImage;
	
	@Column(name="ocr_extracted_text")
	private String ocrExtractedText;
	
	@Column(name="merchant_name")
	private String merchantName;

	@Column(name="merchant_address")
	private String merchantAddress;
	
	@Column(name="scan_date")
	private Date scanDate;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="createdAt")
	private Date createdAt;
	
	@Column(name="updatedAt")
	private Date updatedAt;

	@OneToOne(mappedBy="receipt", cascade=CascadeType.ALL)
	private Expense expense;

	public Receipt() { }
			
	public Receipt(Expense expense, String receiptImage, String ocrExtractedText, String merchantName,
			String merchantAddress, Date scanDate, boolean deleted, Date createdAt, Date updatedAt) {
		this.expense = expense;
		this.receiptImage = receiptImage;
		this.ocrExtractedText = ocrExtractedText;
		this.merchantName = merchantName;
		this.merchantAddress = merchantAddress;
		this.scanDate = scanDate;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public String getReceiptImage() {
		return receiptImage;
	}

	public void setReceiptImage(String receiptImage) {
		this.receiptImage = receiptImage;
	}

	public String getOcrExtractedText() {
		return ocrExtractedText;
	}

	public void setOcrExtractedText(String ocrExtractedText) {
		this.ocrExtractedText = ocrExtractedText;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public Date getScanDate() {
		return scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", receiptImage=" + receiptImage + ", ocrExtractedText=" + ocrExtractedText
				+ ", merchantName=" + merchantName + ", merchantAddress=" + merchantAddress + ", scanDate=" + scanDate
				+ ", deleted=" + deleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
