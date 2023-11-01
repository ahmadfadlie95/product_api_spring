package com.example.hellosprint.controllers;

import com.example.hellosprint.models.Product;
import com.example.hellosprint.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController //declare ini adalah API
@RequestMapping("/api/products")  //ini adalah url dia
@RequiredArgsConstructor //Ini automatically create constructor for me
public class ProductController {

    final ProductService productService;

    @PostMapping //panggil method POST
    public ResponseEntity<?> createProduct(@RequestBody @Valid Product product){ //req body -> data yg dihantar dari body kena ikut format java
        Product product1 = productService.createProduct(product);
    if (product1 != null){
        URI uri = URI.create("/api/products/"+product1.getId());
        return ResponseEntity.created(uri).body(product1);
    }else {
        return ResponseEntity.badRequest().body("Failed to create product");
    }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if (products.size() != 0){
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> findProductById(@PathVariable("id") Long id ){
        Optional<Product> fetchedProduct = productService.findById(id);
        if(fetchedProduct.isPresent()){
            return ResponseEntity.ok(fetchedProduct.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    };

    @PutMapping("/{id}")
    public Optional<Product> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid Product product ){return productService.updateProduct(id,product);}

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){productService.deleteProduct(id);}
}
