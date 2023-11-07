package com.example.hellosprint.data;

import lombok.Data;

    @Data
    public class ProductRequest {
        private Long id;
        private String name;
        private Double price;
        private String description;
        private Long categoryId;
        private String imageUrl;
    }
