package com.ecom.mypersonalproject.controllers;

import com.ecom.mypersonalproject.dtos.ProductRequestDtos;
import com.ecom.mypersonalproject.dtos.ProductResponseDtos;
import com.ecom.mypersonalproject.dtos.patchrequestdtos;
import com.ecom.mypersonalproject.entities.Product;
import com.ecom.mypersonalproject.services.ProductService;
import com.ecom.mypersonalproject.services.ProductServiceMain;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("Myownservice")
    private ProductServiceMain productService;

    public int add(int a,int b){
        return a+b;
    }
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

    // ************** patch product by id ***********
       @PatchMapping("/{id}")
       public ResponseEntity<Product> patchProduct(@PathVariable("id") long id,  @RequestBody patchrequestdtos pro){
           return new ResponseEntity<>(productService.patchProduct(id,pro),HttpStatus.OK);

       }

     // *************** Delete product By id *******
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteByid(@PathVariable("id") long id){
            productService.deleteById(id);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        }

        //count all records
    @GetMapping("/count")
    public long count() {
        return productService.count();
    }

       // @GetMapping("/findAll")
//        public ResponseEntity<List<Product>> getAllProductBypagination(@RequestParam int pagenumber, @RequestParam int pagesize)
//        {
//            List<Product> products = new ArrayList<>();
//            products = productService.geALlProductByPagination(pagenumber, pagesize);
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        }
    }

