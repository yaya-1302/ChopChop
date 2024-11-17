package com.enigma.ChopChop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ingredients")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String category; // Example: 'Vegetable', 'Meat', etc.

    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;
}
