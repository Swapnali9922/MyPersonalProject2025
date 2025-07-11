package com.ecom.mypersonalproject.repositories;

import com.ecom.mypersonalproject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByProductName(String productName);

    List<Product> id(long id);
    //Optional<Product> findById(long id);


    @Override
    Page<Product> findAll(Pageable pageable);
}
