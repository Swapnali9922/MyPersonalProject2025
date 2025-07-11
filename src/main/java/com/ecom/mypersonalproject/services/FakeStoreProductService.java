package com.ecom.mypersonalproject.services;

import com.ecom.mypersonalproject.dtos.FakestoreResponsedto;
import com.ecom.mypersonalproject.dtos.ProductRequestDtos;
import com.ecom.mypersonalproject.dtos.ProductResponseDtos;
import com.ecom.mypersonalproject.dtos.patchrequestdtos;
import com.ecom.mypersonalproject.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service("fakestore")
public class FakeStoreProductService implements ProductServiceMain{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductResponseDtos addNewProduct(ProductRequestDtos dtos) {
        return null;
    }

    @Override
    public Product getProductById(long id) {
         FakestoreResponsedto fakestoreResponsedto= restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakestoreResponsedto.class);
        return convertFakestoreResponsedtotoproduct(fakestoreResponsedto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakestoreResponsedto> fakestoreResponsedto=new ArrayList<FakestoreResponsedto>();

        FakestoreResponsedto[] response= restTemplate.getForObject("https://fakestoreapi.com/products", FakestoreResponsedto[].class);
        fakestoreResponsedto= Arrays.asList(response);

        List<Product> pro=new ArrayList<>();
        for(FakestoreResponsedto dto:fakestoreResponsedto){
            Product product=new Product();
            product=convertFakestoreResponsedtotoproduct(dto);
            pro.add(product);

        }
        return pro;
    }

    @Override
    public ProductResponseDtos updateProduct(long id, ProductRequestDtos pro) {
        return null;
    }

    @Override
    public Product patchProduct(long id, patchrequestdtos pro) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public long count() {
        return 0;
    }

    public Product convertFakestoreResponsedtotoproduct(FakestoreResponsedto fakestoreResponsedto) {
        Product product = new Product();
        product.setId(fakestoreResponsedto.getId());
        product.setPrice((double)fakestoreResponsedto.getPrice());
        return product;
    }

}
