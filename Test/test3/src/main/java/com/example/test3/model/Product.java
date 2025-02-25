package com.example.test3.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "stock")
    private int stock;
}

