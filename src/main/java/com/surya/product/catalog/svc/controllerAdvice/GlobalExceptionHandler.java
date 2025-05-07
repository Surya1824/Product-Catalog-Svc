package com.surya.product.catalog.svc.controllerAdvice;

import com.surya.product.catalog.svc.exception.DAOException;
import com.surya.product.catalog.svc.exception.InvalidInputException;
import com.surya.product.catalog.svc.exception.RoleMismatchError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DAOException.class)
	public ResponseEntity<String> daoExceptionHandler(DAOException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Something went wrong please try after some time.....");
    }
	
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> invalidInputExceptionHandler(InvalidInputException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please check the input and retry.....");
    }

    @ExceptionHandler(RoleMismatchError.class)
    public ResponseEntity<String> handleRoleMismatch(RoleMismatchError re){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re.getMessage());
    }
}
