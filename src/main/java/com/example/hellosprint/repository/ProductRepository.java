package com.example.hellosprint.repository;

import com.example.hellosprint.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
