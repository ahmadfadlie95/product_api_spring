package com.example.hellosprint.models;

import jakarta.persistence.*;
import lombok.Data;

//Entity - this is our model,create in database
@Entity
@Table(name = "products") //I want to override the table name to product - ni nak override the name sahaja
@Data //Generate getter & setter for me  in dart jadi ceni //.poster, .name, .year
//Getter-mendapatkan value, Setter-menetapkan value
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //manage the id for me(auto-generated)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
}
