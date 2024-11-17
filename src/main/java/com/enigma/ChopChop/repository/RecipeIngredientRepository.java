package com.enigma.ChopChop.repository;

import com.enigma.ChopChop.entity.RecipeIngredient;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
    // Find all recipe-ingredient relations by recipe ID
    @Query(value = "SELECT * FROM recipe_ingredients WHERE recipe_id = :recipeId", nativeQuery = true)
    List<RecipeIngredient> findByRecipeId(@Param("recipeId") Long recipeId);

    // Find all recipe-ingredient relations by ingredient ID
    @Query(value = "SELECT * FROM recipe_ingredients WHERE ingredient_id = :ingredientId", nativeQuery = true)
    List<RecipeIngredient> findByIngredientId(@Param("ingredientId") Long ingredientId);

    // Delete a recipe-ingredient relation by ID
    @Modifying
    @Query(value = "DELETE FROM recipe_ingredients WHERE id = :relationId", nativeQuery = true)
    void deleteById(@Param("relationId") @NonNull Long relationId);
}

