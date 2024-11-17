package com.enigma.ChopChop.repository;

import com.enigma.ChopChop.entity.Ingredients;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    @Query(value = "SELECT * FROM ingredients WHERE name = :name", nativeQuery = true)
    Optional<Ingredients> findByName(@Param("name") String name);

    @Modifying
    @Query(value = "DELETE FROM ingredients WHERE id = :ingredientId", nativeQuery = true)
    void deleteById(@Param("ingredientId") @NonNull Long ingredientId);
}

