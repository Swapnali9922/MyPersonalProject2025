package com.ecom.mypersonalproject.controllers;

import com.ecom.mypersonalproject.dtos.ProductRequestDtos;
import com.ecom.mypersonalproject.dtos.ProductResponseDtos;
import com.ecom.mypersonalproject.entities.Product;
import com.ecom.mypersonalproject.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {

    @Autowired
    private ProductService productService;
    // **************create product api***********
    @PostMapping
    public ResponseEntity<ProductResponseDtos> addNewProduct(@Valid @RequestBody ProductRequestDtos pro)
    {
        return new ResponseEntity<>(productService.addNewProduct(pro), HttpStatusCode.valueOf(201));
    }

    // **************GetProductById api************

    @GetMapping ("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productid){
        return new ResponseEntity<>(productService.getProductById(productid),HttpStatus.OK);
    }


    //**************** Get All products ********
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products=new ArrayList<>();
        products=productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


    // ************* update product by id *********
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDtos> updateproductById(@PathVariable("id") long id, @Valid @RequestBody ProductRequestDtos pro){
             return new ResponseEntity<>(productService.updateProduct(id,pro),HttpStatus.OK);
    }
}
