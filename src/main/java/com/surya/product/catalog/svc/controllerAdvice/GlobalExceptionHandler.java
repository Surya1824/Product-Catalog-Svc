package com.surya.product.catalog.svc;

import com.surya.product.catalog.svc.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runTimeExceptionHandler(RuntimeException re){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Something went wrong please try After some time.....");
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalidInputExceptionHandler(InvalidInputException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please check the input and retry.....");
    }
}
