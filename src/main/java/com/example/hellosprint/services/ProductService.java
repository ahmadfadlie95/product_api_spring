package com.example.hellosprint.services;

import com.example.hellosprint.models.Product;

import java.util.List;

//inside interface, list all the methods in impl
public interface ProductService {

    Product createProduct(Product newProduct);

    List<Product> getAllProducts();
}
