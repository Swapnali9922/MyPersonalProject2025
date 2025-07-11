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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service("Myownservice")
public class ProductService implements ProductServiceMain{

    @Autowired
    private ProductRepository productRepository;

    private EmailService emailService;

    public ProductService(EmailService emailService) {
        this.emailService = emailService;
    }

    public ProductResponseDtos addNewProduct(ProductRequestDtos dtos) {
        Optional<Product> pro= productRepository.findByProductName(dtos.getProductName());
        if(pro.isPresent()) {
            throw new productExistException("This product is already exists!!!");
        }
        Product product = new Product();
        product.setProductName(dtos.getProductName());
        product.setPrice(dtos.getPrice());

        Product save = productRepository.save(product);
        String recepient="swapnalipatil2801@gmail.com";
        emailService.sendProductCreatedEmail(recepient, save);
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

     public void deleteById(long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            String recepient="swapnalipatil2801@gmail.com";
            emailService.deletedproductmail(recepient,id);
        }

        else
            throw new productExistException("product Id is not found!!!");

     }
     public long count() {
        long c=productRepository.count();
                return c;
     }
//     public List<Product> geALlProductByPagination(int pagenumber,int pagesize){
//        Page<Product> page=productRepository.findAll(PageRequest.of(
//                 pagenumber,pagesize, (Sort.by("productName"))
//         ));
//        List<Product> pro=page.getContent();
//        return pro;

    // }




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
