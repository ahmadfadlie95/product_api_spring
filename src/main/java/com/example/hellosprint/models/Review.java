package com.example.hellosprint.models;

import com.example.hellosprint.data.ProductRequest;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rating;
    private String review;

    private User user;
    private Product product;
}
