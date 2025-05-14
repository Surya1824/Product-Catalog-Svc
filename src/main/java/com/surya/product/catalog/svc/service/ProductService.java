package com.surya.product.catalog.svc.service;

import com.surya.product.catalog.svc.exception.InvalidInputException;
import com.surya.product.catalog.svc.exception.DAOException;
import com.surya.product.catalog.svc.repository.SubCategoryRepository;
import com.surya.product.catalog.svc.model.SubCategory;
import com.surya.product.catalog.svc.repository.ProductRepository;
import com.surya.product.catalog.svc.model.Inventory;
import com.surya.product.catalog.svc.model.OperationEnum;
import com.surya.product.catalog.svc.model.Product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final SubCategoryRepository subCategoryRepository;
    
    private final ProductMQProducer producMQProducer;

    public ProductService(ProductRepository productRepository, SubCategoryRepository subCategoryRepository, ProductMQProducer producMQProducer) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
		this.producMQProducer = producMQProducer;
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
            List<Product> savedProducts = productRepository.saveAll(products);           	
            
            //Updating Inventory by MQ
			List<Inventory> productMQMessages = savedProducts.stream().map(p -> {
				return new Inventory(UUID.randomUUID().toString(), p.getProductId(), p.getName(), p.getBrand(),
						p.getQuantity(), OperationEnum.CREATED, LocalDateTime.now());}).collect(Collectors.toList());
			producMQProducer.updateInventory(productMQMessages);
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Products Added Successfully");
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Products Already Exist");
        }
    }

    public ResponseEntity<List<Product>> getProductDetails() throws DAOException {
        try{
            List<Product> products = productRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
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

    public ResponseEntity<String> updateProductDetails(Product product) throws DAOException {
        try{
            Product updatedProduct = productRepository.save(product);
            
            producMQProducer.updateInventory(Arrays.asList(new Inventory(UUID.randomUUID().toString(),updatedProduct.getProductId(),updatedProduct.getName(),
            		updatedProduct.getBrand(), updatedProduct.getQuantity(), OperationEnum.UPDATE,LocalDateTime.now())));
            
            return ResponseEntity.status(HttpStatus.OK).body("Updated Product Details Successfully");
        } catch (Exception e) {
            throw  new DAOException(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) throws InvalidInputException {
        try{
            productRepository.deleteByProductId(id);
            
            producMQProducer.updateInventory(Arrays.asList(new Inventory(UUID.randomUUID().toString(),id,"Null", "Null",
            	0l, OperationEnum.REMOVE,LocalDateTime.now())));
            
            return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Successfully");
        } catch (Exception e) {
            throw new InvalidInputException("Invalid Input");
        }
    }
}
