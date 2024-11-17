package com.enigma.ChopChop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredients ingredient;

    @Column(nullable = false)
    private Double quantity; // Example: 2.0

    private String unit; // Example: 'grams', 'cups', etc.
}

