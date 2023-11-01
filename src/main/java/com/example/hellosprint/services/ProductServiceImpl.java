package com.example.hellosprint.services;

import com.example.hellosprint.models.Product;
import com.example.hellosprint.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service //Declare dia as service
@RequiredArgsConstructor //Automatically create constructor and inject dependency //lombok tolong buatkan constructor for us
public class ProductServiceImpl implements ProductService{


    final ProductRepository productRepository;
    //Create
    public Product createProduct(Product newProduct){
        return productRepository.save(newProduct);

    }


    //Read all
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }




    //Read by Id
    public Optional <Product> findById(Long id){ return productRepository.findById(id);}


    //Update
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> updatedProduct = productRepository.findById(id);
        if (updatedProduct.isPresent()) {
            Product existingProduct = updatedProduct.get();
            existingProduct.setDescription(product.getDescription());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
            return Optional.of(productRepository.save(existingProduct));
        }
        return Optional.empty();
    }



    //Delete
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
