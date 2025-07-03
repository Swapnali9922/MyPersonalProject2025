package com.ecom.mypersonalproject.services;

import com.ecom.mypersonalproject.dtos.ProductRequestDtos;
import com.ecom.mypersonalproject.dtos.ProductResponseDtos;
import com.ecom.mypersonalproject.dtos.patchrequestdtos;
import com.ecom.mypersonalproject.entities.Product;
import com.ecom.mypersonalproject.exceptions.EmptyList;
import com.ecom.mypersonalproject.exceptions.ProductnotfoundException;
import com.ecom.mypersonalproject.exceptions.productExistException;
import com.ecom.mypersonalproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDtos addNewProduct(ProductRequestDtos dtos) {
        Optional<Product> pro= productRepository.findByProductName(dtos.getProductName());
        if(pro.isPresent()) {
            throw new productExistException("This product is already exists!!!");
        }
        Product product = new Product();
        product.setProductName(dtos.getProductName());
        product.setPrice(dtos.getPrice());

        Product save = productRepository.save(product);

        ProductResponseDtos response = convertProducttoProductResponseDtos(save);
        return response;


    }

    public Product getProductById(long id) {
        Optional<Product> product= productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        }
        else {
            throw new ProductnotfoundException("product not found!!!");
        }
    }

    public List<Product> getAllProducts() {
        Optional<List<Product>> pros= Optional.of(productRepository.findAll());
        if(pros.isEmpty())
            throw new EmptyList("No records found!!!");
        else
            return pros.get();
    }

    public ProductResponseDtos updateProduct(long id,ProductRequestDtos pro){
            if(productRepository.existsById(id))
            {
                Product product=productRepository.findById(id).get();
                product.setProductName(pro.getProductName());
                product.setPrice(pro.getPrice());
                productRepository.save(product);
                ProductResponseDtos response = convertProducttoProductResponseDtos(product);
                return response;
            }
            else
                throw new productExistException("product Id is not found!!!");
    }

     public Product patchProduct(long id, patchrequestdtos pro){
        //logging
         //  System.out.println("PATCH called for ID: " + id);
       //  System.out.println("Incoming DTO: name=" + pro.getName() + ", price=" + pro.getPrice());
                if(productRepository.existsById(id)){
                    Product product=productRepository.findById(id).get();
                    if(pro.getName()!=null)
                    {
                        product.setProductName(pro.getName());
                    }
                    if(pro.getPrice() != null)
                    {
                        product.setPrice(pro.getPrice());
                    }
                Product response=  productRepository.save(product);

                   return response;
                }
                else
                    throw new productExistException("product Id is not found!!!");
     }
    public ProductResponseDtos convertProducttoProductResponseDtos(Product product) {

        ProductResponseDtos response = new ProductResponseDtos();
        response.setProductName(product.getProductName());
        response.setProductPrice(product.getPrice());
        response.setProductId(product.getId());
        response.setStatus("Product successfully added");
        response.setCreatedAt(LocalDateTime.now());
        return response;
    }

}
