package com.ecom.mypersonalproject.services;

import com.ecom.mypersonalproject.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendProductCreatedEmail(String to, Product product) {
         SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(to);
         message.setSubject("new product created successfully");
         message.setText("This is the product"+product.getProductName()+"price is: "+product.getPrice());
         mailSender.send(message);
    }

    @Async
    public void deletedproductmail(String to, long id) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("deleted product successfully");
        message.setText("product number:"+id+"has been deleted successfully");
        javaMailSender.send(message);

    }
}
