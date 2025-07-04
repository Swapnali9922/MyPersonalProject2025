package com.ecom.mypersonalproject.controllers;

import com.ecom.mypersonalproject.entities.Product;
import com.ecom.mypersonalproject.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {


    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productservice;

    @Test
    void getProductById() {
        //Arrange
        Product p1=new Product();
        p1.setId(3);
        p1.setProductName("test 1");
        p1.setPrice(100.0);
        Mockito.when(productservice.getProductById(2)).thenReturn(p1);
        //act
        ResponseEntity<Product> actual=productController.getProductById(2);
        assertEquals(p1,actual.getBody());

    }

    @Test
    void getAllProducts() {
        Product p1=new Product();
        p1.setId(1);
        p1.setProductName("test 1");
        p1.setPrice(100.0);
        Product p2=new Product();
        p2.setId(2);
        p2.setProductName("test 2");
        p2.setPrice(200.0);
        Product p3=new Product();
        p3.setId(3);
        p3.setProductName("test 3");
      //  p3.setPrice(300.0);


        List<Product> testproducts=List.of(p1,p2,p3);
        //sp pls don't make a actual call to product service instead use hardcode the value
        Mockito.when(productservice.getAllProducts()).thenReturn(testproducts);

        ResponseEntity<List<Product>> actualresponse= productController.getAllProducts();

        assertEquals(testproducts.size(),actualresponse.getBody().size());
        assertEquals(actualresponse.getBody(),testproducts);
    }

    @Test
    void add() {

        /*Arrange----Arranging all input and expected output against the test case*/
        int a=10;
        int b=20;
        int expected=0;

        /* Act: run a actual code here */
        int actual=productController.add(a,b);

        //Assert
        assertEquals(expected,actual);
    }
}