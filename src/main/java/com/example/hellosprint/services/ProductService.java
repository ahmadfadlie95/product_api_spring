package com.example.hellosprint.services;

import com.example.hellosprint.data.ProductRequest;
import com.example.hellosprint.models.Product;

import java.util.List;
import java.util.Optional;

//inside interface, list all the methods in impl
public interface ProductService {

    ProductRequest createProduct(ProductRequest newProduct);

    List<ProductRequest> getAllProducts();
    List<ProductRequest> findByCategory(Long categoryId);


    Optional<Product> findById(Long id);

    Optional<Product> updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
