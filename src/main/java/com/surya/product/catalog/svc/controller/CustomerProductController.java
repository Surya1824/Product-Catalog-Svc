package com.surya.product.catalog.svc.controller;

import com.surya.product.catalog.svc.model.Product;
import com.surya.product.catalog.svc.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerProductController {

    private final ProductService productService;

    public CustomerProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return productService.getProductDetails();
    }

    @GetMapping("/filter/products")
    public ResponseEntity<List<Product>> getProducts(@PathParam(value = "filter") String filter){

        System.out.println("Path param: " + filter);
        return productService.getProductDetails(filter);
    }

    @GetMapping("/products/{Id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "Id") Integer id){
        return productService.getProductDetail(id);
    }

}
