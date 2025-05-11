package com.surya.product.catalog.svc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotNull(message = "Product Name shouldn't be null")
    @Column(unique = true)
    private String name;
    @NotNull(message = "Product Brand shouldn't be null")
    private String brand;
    @NotNull(message = "Product Description Name shouldn't be null")
    private String description;
    @NotNull(message = "Product specification Name shouldn't be null")
    private String specification;
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @NotNull(message = "Product originalPrice Name shouldn't be null")
    private Double originalPrice;
    private Integer discount;
    private Double discountPrice;
    @NotNull(message = "Product Quantity shouldn't be null")
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", brand=" + brand + ", description="
				+ description + ", specification=" + specification + ", category=" + category + ", subCategory="
				+ subCategory + ", originalPrice=" + originalPrice + ", discount=" + discount + ", discountPrice="
				+ discountPrice + ", quantity=" + quantity + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
    
}
