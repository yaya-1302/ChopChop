package com.enigma.ChopChop.repository;

import com.enigma.ChopChop.entity.Recipe;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipes WHERE user_id = :userId", nativeQuery = true)
    List<Recipe> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM recipes WHERE category = :category", nativeQuery = true)
    List<Recipe> findByCategory(@Param("category") String category);

    @Modifying
    @Query(value = "DELETE FROM recipes WHERE id = :recipeId", nativeQuery = true)
    void deleteById(@Param("recipeId") @NonNull Long recipeId);
}

