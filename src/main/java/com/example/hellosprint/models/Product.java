package com.example.hellosprint.models;

import com.example.hellosprint.data.ProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

//Entity - this is our model,create in database
@Entity
@Table(name = "products") //I want to override the table name to product - ni nak override the name sahaja
@Data //Generate getter & setter for me  in dart jadi ceni //.poster, .name, .year
//Getter-mendapatkan value, Setter-menetapkan value
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //manage the id for me(auto-generated)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_id", nullable = false) //dekat parent takde joincolumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;


    // Automatically dia akan keluar bila API find dipanggil
    // Keluar di dalam []
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;
    //product to json transformer

    //product to json transformer
    @JsonIgnore
    public ProductRequest getDto() {
        ProductRequest productDto = new ProductRequest();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setImageUrl(imageUrl);
        productDto.setCategoryId(category.getId());
        return productDto;
    }



}
