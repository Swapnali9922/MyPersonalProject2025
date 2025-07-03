package com.ecom.mypersonalproject.repositories;

import com.ecom.mypersonalproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByProductName(String productName);
    //Optional<Product> findById(long id);
}
