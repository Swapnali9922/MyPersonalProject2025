package com.ecom.mypersonalproject.advises;


import com.ecom.mypersonalproject.exceptions.EmptyList;
import com.ecom.mypersonalproject.exceptions.ProductnotfoundException;
import com.ecom.mypersonalproject.exceptions.productExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(productExistException.class)
    public ResponseEntity<String> productduplicate(productExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ProductnotfoundException.class)
    public ResponseEntity<String> productNotfound(ProductnotfoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyList.class)
    public ResponseEntity<String> emptylist(EmptyList e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
