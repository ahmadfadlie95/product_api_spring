package com.example.hellosprint.models;

import com.example.hellosprint.data.ProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //manage the id for me(auto-generated)
    private Long id;

    private Integer rating;
    private String review;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="user_id", nullable = false) //dekat parent takde joincolumn
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable = false) //dekat parent takde joincolumn
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Product product;
}