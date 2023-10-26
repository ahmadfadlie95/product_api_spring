package com.example.hellosprint.controllers;

import com.example.hellosprint.models.Product;
import com.example.hellosprint.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //declare ini adalah API
@RequestMapping("/api/products")  //ini adalah url dia
@RequiredArgsConstructor //Ini automatically create constructor for me
public class ProductController {

    final ProductService productService;

    @PostMapping //panggil method POST
    public Product createProduct(@RequestBody Product product){ //req body -> data yg dihantar dari body kena ikut format java
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
