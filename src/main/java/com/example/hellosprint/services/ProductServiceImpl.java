package com.example.hellosprint.services;

import com.example.hellosprint.models.Product;
import com.example.hellosprint.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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



    //Update



    //Delete



}
