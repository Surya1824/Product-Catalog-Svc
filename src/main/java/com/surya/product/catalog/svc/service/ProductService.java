package com.surya.product.catalog.svc.service;

import com.surya.product.catalog.svc.exception.InvalidInputException;
import com.surya.product.catalog.svc.repository.SubCategoryRepository;
import com.surya.product.catalog.svc.model.SubCategory;
import com.surya.product.catalog.svc.repository.ProductRepository;

import com.surya.product.catalog.svc.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final SubCategoryRepository subCategoryRepository;

    public ProductService(ProductRepository productRepository, SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public ResponseEntity<String> addProducts(@Valid List<Product> products) {
        try {
            for (Product product : products) {
                SubCategory subCategory = subCategoryRepository.findByName(product.getSubCategory().getName())
                        .orElseGet(() -> {
                            System.out.println("Creating new SubCategory: " + product.getSubCategory().getName());
                            return subCategoryRepository.save(product.getSubCategory());
                        });

                product.setSubCategory(subCategory);
            }
            productRepository.saveAll(products);
            return ResponseEntity.status(HttpStatus.CREATED).body("Products Added Successfully");
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Products Already Exist");
        }
    }

    public ResponseEntity<List<Product>> getProductDetails() {
        try{
            List<Product> products = productRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<Product>> getProductDetails(String filterValue) throws InvalidInputException {
        try{
            List<Product> products = productRepository.searchProducts(filterValue);
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid Input");
        }
    }

    public ResponseEntity<Product> getProductDetail(Integer id) throws InvalidInputException {
        try{
            Optional<Product> products = productRepository.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(products.orElse(null));
        } catch (Exception e) {
            throw new InvalidInputException("Invalid Input");
        }
    }

    public ResponseEntity<String> updateProductDetails(Product product) {
        try{
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Updated Product Details Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteProduct(Integer id) throws InvalidInputException {
        try{
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Successfully");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid Input");
        }
    }
}
