package com.surya.product.catalog.svc.controller;

import com.surya.product.catalog.svc.exception.InvalidInputException;
import com.surya.product.catalog.svc.model.Product;
import com.surya.product.catalog.svc.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add/products")
    public ResponseEntity<String> addProducts(@RequestBody @Valid List<Product> products){

        System.out.println("List of Products: " + products);
        return productService.addProducts(products);
    }

    @PutMapping("/product")
    public ResponseEntity<String> updateProductDetails(@RequestBody Product product){
        return productService.updateProductDetails(product);
    }

    @DeleteMapping("/product/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "Id") Integer id) throws InvalidInputException {
        return productService.deleteProduct(id);
    }

}
