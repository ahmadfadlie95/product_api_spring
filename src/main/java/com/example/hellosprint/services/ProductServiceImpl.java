package com.example.hellosprint.services;

import com.example.hellosprint.data.ProductRequest;
import com.example.hellosprint.models.Category;
import com.example.hellosprint.models.Product;
import com.example.hellosprint.repository.CategoryRepository;
import com.example.hellosprint.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //Declare dia as service
@RequiredArgsConstructor //Automatically create constructor and inject dependency //lombok tolong buatkan constructor for us
public class ProductServiceImpl implements ProductService{


    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    //Create
    public ProductRequest createProduct(ProductRequest productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        //from category id pass, category repository look for the category and link it to product
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();

    }




    //Read all
    public List<ProductRequest> getAllProducts(){

        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }



    //Read by Id
    public Optional <Product> findById(Long id){ return productRepository.findById(id);}
    public List<ProductRequest> findByCategory(Long categoryId){
        List<Product> products = productRepository.findByCategory_Id(categoryId);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }




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
