package com.example.hellosprint.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data //build getter & setter, constructor for me, hanya boleh bila guna lombok
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
