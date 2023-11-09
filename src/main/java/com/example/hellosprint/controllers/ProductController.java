package com.example.hellosprint.controllers;

import com.example.hellosprint.data.ProductRequest;
import com.example.hellosprint.data.ReviewRequest;
import com.example.hellosprint.models.Product;
import com.example.hellosprint.models.User;
import com.example.hellosprint.services.ProductService;
import com.example.hellosprint.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController //declare ini adalah API
@RequestMapping("/api/products")  //ini adalah url dia
@RequiredArgsConstructor //Ini automatically create constructor for me
public class ProductController {

    final ProductService productService;
    final ReviewService reviewService;

    @PostMapping //panggil method POST
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest product){ //req body -> data yg dihantar dari body kena ikut format java
        ProductRequest product1 = productService.createProduct(product);
    if (product1 != null){
        URI uri = URI.create("/api/products/"+product1.getId());
        return ResponseEntity.created(uri).body(product1);
    }else {
        return ResponseEntity.badRequest().body("Failed to create product");
    }
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductRequest> products = productService.getAllProducts();
        if (products.size() != 0){
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductRequest>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductRequest> products = productService.findByCategory(categoryId);
        return ResponseEntity.ok(products);
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

    @PostMapping("/{id}/reviews")
    public ResponseEntity <?> createReview(@PathVariable("id") Long productId, @RequestBody ReviewRequest request, Authentication authentication) throws Exception {
        //Dapatkan id user dari jwt header
        User currentUser = (User) authentication.getPrincipal();
        Long userId = (Long)currentUser.getId();

        return ResponseEntity.ok(reviewService.addReview(request,productId,userId));
    }
}
