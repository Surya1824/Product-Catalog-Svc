package com.surya.product.catalog.svc.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductMQMessage {
	
	private String messageId;
	private Long productId;
	private String productName;
	private String productBrand;
	private Long quantity;
	private OperationEnum oprationEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

	public ProductMQMessage(String messageId, Long productId, String productName, String productBrand, Long quantity, OperationEnum oprationEnum, LocalDateTime createdAt) {
		super();
		this.messageId = messageId;
		this.productId = productId;
		this.productName = productName;
		this.productBrand = productBrand;
		this.quantity = quantity;
		this.oprationEnum = oprationEnum;
		this.createdAt = createdAt;
	}
	

	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public OperationEnum getOprationEnum() {
		return oprationEnum;
	}

	public void setOprationEnum(OperationEnum oprationEnum) {
		this.oprationEnum = oprationEnum;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "ProductMQMessage [messageId=" + messageId + ", productId=" + productId + ", productName=" + productName
				+ ", productBrand=" + productBrand + ", quantity=" + quantity + ", oprationEnum=" + oprationEnum
				+ ", createdAt=" + createdAt + "]";
	}
    
}
