package com.surya.product.catalog.svc.controller;

import com.surya.product.catalog.svc.exception.DAOException;
import com.surya.product.catalog.svc.exception.InvalidInputException;
import com.surya.product.catalog.svc.exception.RoleMismatchError;
import com.surya.product.catalog.svc.model.Product;
import com.surya.product.catalog.svc.service.ProductService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
	
	private final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    public final String ADMIN_ROLE = "ADMIN";

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add/products")
    public ResponseEntity<String> addProducts(@RequestBody @Valid List<Product> products, @RequestHeader(name = "User-Type") String userType) throws RoleMismatchError{
    	logger.info("Entry addProducts: {}", userType);
        if(userType.equalsIgnoreCase(ADMIN_ROLE)){
        	logger.info("List of Products: {}", products);
            return productService.addProducts(products);
        }else{
            throw new RoleMismatchError("You do not have the required role to access this resource.");
        }

    }

    @PutMapping("/product")
    public ResponseEntity<String> updateProductDetails(@RequestBody Product product,@RequestHeader(name = "User-Type") String userType) throws RoleMismatchError, DAOException{
        if(userType.equalsIgnoreCase(ADMIN_ROLE)){
            System.out.println(" Products: " + product);
            return productService.updateProductDetails(product);
        }else{
            throw new RoleMismatchError("You do not have the required role to access this resource.");
        }
    }

    @DeleteMapping("/product/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "Id") Integer id,@RequestHeader(name = "User-Type") String userType) throws InvalidInputException, RoleMismatchError {
        if(userType.equalsIgnoreCase(ADMIN_ROLE)){
            System.out.println("Product Id: " + id);
            return productService.deleteProduct(id);
        }else{
            throw new RoleMismatchError("You do not have the required role to access this resource.");
        }
    }

}
