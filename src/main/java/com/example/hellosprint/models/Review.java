package com.example.hellosprint.models;

import com.example.hellosprint.data.ProductRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rating;
    private String review;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false) //dekat parent takde joincolumn
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable = false) //dekat parent takde joincolumn
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;
}