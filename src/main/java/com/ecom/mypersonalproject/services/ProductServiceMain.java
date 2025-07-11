package com.ecom.mypersonalproject.services;

import com.ecom.mypersonalproject.dtos.ProductRequestDtos;
import com.ecom.mypersonalproject.dtos.ProductResponseDtos;
import com.ecom.mypersonalproject.dtos.patchrequestdtos;
import com.ecom.mypersonalproject.entities.Product;

import java.util.List;

public interface ProductServiceMain {
    public ProductResponseDtos addNewProduct(ProductRequestDtos dtos);
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public ProductResponseDtos updateProduct(long id,ProductRequestDtos pro);
    public Product patchProduct(long id, patchrequestdtos pro);
    public void deleteById(long id);
    public long count();


}
